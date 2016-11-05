package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class APPLY_FRICTION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public APPLY_FRICTION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Obj) && NAT.instance.has(Elapsed) && machine.get_lanes().has(Lane) && machine.get_acc().apply(Obj).equals(new Integer(0)) && (new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed))).compareTo(new Integer(0)) >= 0); */
	public /*@ pure */ boolean guard_APPLY_FRICTION( Integer Elapsed, Integer Lane, Integer Obj) {
		return (machine.get_cars().has(Obj) && NAT.instance.has(Elapsed) && machine.get_lanes().has(Lane) && machine.get_acc().apply(Obj).equals(new Integer(0)) && (new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed))).compareTo(new Integer(0)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_APPLY_FRICTION(Elapsed,Lane,Obj);
		assignable machine.vel;
		ensures guard_APPLY_FRICTION(Elapsed,Lane,Obj) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * 5 * Elapsed)))))))); 
	 also
		requires !guard_APPLY_FRICTION(Elapsed,Lane,Obj);
		assignable \nothing;
		ensures true; */
	public void run_APPLY_FRICTION( Integer Elapsed, Integer Lane, Integer Obj){
		if(guard_APPLY_FRICTION(Elapsed,Lane,Obj)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(vel_tmp.apply(Obj) - new Integer(machine.get_friction().apply(Lane) * 5 * Elapsed)))))));

			System.out.println("APPLY_FRICTION executed Elapsed: " + Elapsed + " Lane: " + Lane + " Obj: " + Obj + " ");
		}
	}

}
