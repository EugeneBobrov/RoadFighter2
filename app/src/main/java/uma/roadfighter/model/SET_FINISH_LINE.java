package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_FINISH_LINE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_FINISH_LINE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane) && INT.instance.has(F)); */
	public /*@ pure */ boolean guard_SET_FINISH_LINE( Integer F, Integer Lane) {
		return (machine.get_lanes().has(Lane) && INT.instance.has(F));
	}

	/*@ public normal_behavior
		requires guard_SET_FINISH_LINE(F,Lane);
		assignable machine.finish_line;
		ensures guard_SET_FINISH_LINE(F,Lane) &&  machine.get_finish_line().equals(\old((machine.get_finish_line().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,F)))))); 
	 also
		requires !guard_SET_FINISH_LINE(F,Lane);
		assignable \nothing;
		ensures true; */
	public void run_SET_FINISH_LINE( Integer F, Integer Lane){
		if(guard_SET_FINISH_LINE(F,Lane)) {
			BRelation<Integer,Integer> finish_line_tmp = machine.get_finish_line();

			machine.set_finish_line((finish_line_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,F)))));

			System.out.println("SET_FINISH_LINE executed F: " + F + " Lane: " + Lane + " ");
		}
	}

}
