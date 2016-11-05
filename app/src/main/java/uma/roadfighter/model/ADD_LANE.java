package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class ADD_LANE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_LANE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.LANES.difference(machine.get_lanes()).has(Lane) && INT.instance.has(Left) && INT.instance.has(Right) && (Left).compareTo(Right) < 0 && INT.instance.has(Finish) && NAT.instance.has(F)); */
	public /*@ pure */ boolean guard_ADD_LANE( Integer Finish, Integer Lane, Integer Left, Integer Right, Integer F) {
		return (machine.LANES.difference(machine.get_lanes()).has(Lane) && INT.instance.has(Left) && INT.instance.has(Right) && (Left).compareTo(Right) < 0 && INT.instance.has(Finish) && NAT.instance.has(F));
	}

	/*@ public normal_behavior
		requires guard_ADD_LANE(Finish,Lane,Left,Right,F);
		assignable machine.lanes, machine.finish_line, machine.left_border, machine.right_border, machine.friction;
		ensures guard_ADD_LANE(Finish,Lane,Left,Right,F) &&  machine.get_lanes().equals(\old((machine.get_lanes().union(new BSet<Integer>(Lane))))) &&  machine.get_finish_line().equals(\old((machine.get_finish_line().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Finish)))))) &&  machine.get_left_border().equals(\old((machine.get_left_border().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Left)))))) &&  machine.get_right_border().equals(\old((machine.get_right_border().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Right)))))) &&  machine.get_friction().equals(\old((machine.get_friction().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,F)))))); 
	 also
		requires !guard_ADD_LANE(Finish,Lane,Left,Right,F);
		assignable \nothing;
		ensures true; */
	public void run_ADD_LANE( Integer Finish, Integer Lane, Integer Left, Integer Right, Integer F){
		if(guard_ADD_LANE(Finish,Lane,Left,Right,F)) {
			BSet<Integer> lanes_tmp = machine.get_lanes();
			BRelation<Integer,Integer> finish_line_tmp = machine.get_finish_line();
			BRelation<Integer,Integer> left_border_tmp = machine.get_left_border();
			BRelation<Integer,Integer> right_border_tmp = machine.get_right_border();
			BRelation<Integer,Integer> friction_tmp = machine.get_friction();

			machine.set_lanes((lanes_tmp.union(new BSet<Integer>(Lane))));
			machine.set_finish_line((finish_line_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Finish)))));
			machine.set_left_border((left_border_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Left)))));
			machine.set_right_border((right_border_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,Right)))));
			machine.set_friction((friction_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,F)))));

			System.out.println("ADD_LANE executed Finish: " + Finish + " Lane: " + Lane + " Left: " + Left + " Right: " + Right + " F: " + F + " ");
		}
	}

}
