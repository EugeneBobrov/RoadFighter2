package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_ZERO_VEL{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_ZERO_VEL(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && (machine.get_vel().apply(Car)).compareTo(new Integer(0)) < 0); */
	public /*@ pure */ boolean guard_SET_ZERO_VEL( Integer Car) {
		return (machine.get_cars().has(Car) && (machine.get_vel().apply(Car)).compareTo(new Integer(0)) < 0);
	}

	/*@ public normal_behavior
		requires guard_SET_ZERO_VEL(Car);
		assignable machine.vel;
		ensures guard_SET_ZERO_VEL(Car) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))); 
	 also
		requires !guard_SET_ZERO_VEL(Car);
		assignable \nothing;
		ensures true; */
	public void run_SET_ZERO_VEL( Integer Car){
		if(guard_SET_ZERO_VEL(Car)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));

			System.out.println("SET_ZERO_VEL executed Car: " + Car + " ");
		}
	}

}
