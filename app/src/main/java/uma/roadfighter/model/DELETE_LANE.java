package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class DELETE_LANE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public DELETE_LANE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.LANES.difference(machine.get_lanes()).has(Lane); */
	public /*@ pure */ boolean guard_DELETE_LANE( Integer Lane) {
		return machine.LANES.difference(machine.get_lanes()).has(Lane);
	}

	/*@ public normal_behavior
		requires guard_DELETE_LANE(Lane);
		assignable machine.lanes, machine.finish_line, machine.left_border, machine.right_border;
		ensures guard_DELETE_LANE(Lane) &&  machine.get_lanes().equals(\old(machine.get_lanes().difference(new BSet<Integer>(Lane)))) &&  machine.get_finish_line().equals(\old(machine.get_finish_line().domainSubtraction(new BSet<Integer>(Lane)))) &&  machine.get_left_border().equals(\old(machine.get_left_border().domainSubtraction(new BSet<Integer>(Lane)))) &&  machine.get_right_border().equals(\old(machine.get_right_border().domainSubtraction(new BSet<Integer>(Lane)))); 
	 also
		requires !guard_DELETE_LANE(Lane);
		assignable \nothing;
		ensures true; */
	public void run_DELETE_LANE( Integer Lane){
		if(guard_DELETE_LANE(Lane)) {
			BSet<Integer> lanes_tmp = machine.get_lanes();
			BRelation<Integer,Integer> finish_line_tmp = machine.get_finish_line();
			BRelation<Integer,Integer> left_border_tmp = machine.get_left_border();
			BRelation<Integer,Integer> right_border_tmp = machine.get_right_border();

			machine.set_lanes(lanes_tmp.difference(new BSet<Integer>(Lane)));
			machine.set_finish_line(finish_line_tmp.domainSubtraction(new BSet<Integer>(Lane)));
			machine.set_left_border(left_border_tmp.domainSubtraction(new BSet<Integer>(Lane)));
			machine.set_right_border(right_border_tmp.domainSubtraction(new BSet<Integer>(Lane)));

			System.out.println("DELETE_LANE executed Lane: " + Lane + " ");
		}
	}

}
