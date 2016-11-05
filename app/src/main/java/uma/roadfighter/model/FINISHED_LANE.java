package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class FINISHED_LANE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public FINISHED_LANE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && machine.get_lanes().has(Lane)); */
	public /*@ pure */ boolean guard_FINISHED_LANE( Integer Car, Integer Lane) {
		return (machine.get_cars().has(Car) && machine.get_lanes().has(Lane));
	}

	/*@ public normal_behavior
		requires guard_FINISHED_LANE(Car,Lane);
		assignable machine.finished;
		ensures guard_FINISHED_LANE(Car,Lane) &&  machine.get_finished().equals(\old((machine.get_finished().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,((machine.get_posY().apply(Car)).compareTo(machine.get_finish_line().apply(Lane)) > 0))))))); 
	 also
		requires !guard_FINISHED_LANE(Car,Lane);
		assignable \nothing;
		ensures true; */
	public void run_FINISHED_LANE( Integer Car, Integer Lane){
		if(guard_FINISHED_LANE(Car,Lane)) {
			BRelation<Integer,Boolean> finished_tmp = machine.get_finished();

			machine.set_finished((finished_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,((machine.get_posY().apply(Car)).compareTo(machine.get_finish_line().apply(Lane)) > 0))))));

			System.out.println("FINISHED_LANE executed Car: " + Car + " Lane: " + Lane + " ");
		}
	}

}
