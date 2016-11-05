package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_LEFT_BORDER{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_LEFT_BORDER(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane) && INT.instance.has(B)); */
	public /*@ pure */ boolean guard_SET_LEFT_BORDER( Integer B, Integer Lane) {
		return (machine.get_lanes().has(Lane) && INT.instance.has(B));
	}

	/*@ public normal_behavior
		requires guard_SET_LEFT_BORDER(B,Lane);
		assignable machine.left_border;
		ensures guard_SET_LEFT_BORDER(B,Lane) &&  machine.get_left_border().equals(\old((machine.get_left_border().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,B)))))); 
	 also
		requires !guard_SET_LEFT_BORDER(B,Lane);
		assignable \nothing;
		ensures true; */
	public void run_SET_LEFT_BORDER( Integer B, Integer Lane){
		if(guard_SET_LEFT_BORDER(B,Lane)) {
			BRelation<Integer,Integer> left_border_tmp = machine.get_left_border();

			machine.set_left_border((left_border_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,B)))));

			System.out.println("SET_LEFT_BORDER executed B: " + B + " Lane: " + Lane + " ");
		}
	}

}
