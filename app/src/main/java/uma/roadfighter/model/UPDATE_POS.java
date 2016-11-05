package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_POS{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_POS(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (NAT.instance.has(Elapsed) && machine.get_cars().has(Obj)); */
	public /*@ pure */ boolean guard_UPDATE_POS( Integer Elapsed, Integer Obj) {
		return (NAT.instance.has(Elapsed) && machine.get_cars().has(Obj));
	}

	/*@ public normal_behavior
		requires guard_UPDATE_POS(Elapsed,Obj);
		assignable machine.posX, machine.posY;
		ensures guard_UPDATE_POS(Elapsed,Obj) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(machine.get_posX().apply(Obj) + new Integer(new Integer(machine.get_drift().apply(Obj) * Elapsed * 50) / 1000)))))))) &&  machine.get_posY().equals(\old((machine.get_posY().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(machine.get_posY().apply(Obj) + new Integer(new Integer(machine.get_vel().apply(Obj) * Elapsed) / 1000)))))))); 
	 also
		requires !guard_UPDATE_POS(Elapsed,Obj);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_POS( Integer Elapsed, Integer Obj){
		if(guard_UPDATE_POS(Elapsed,Obj)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(posX_tmp.apply(Obj) + new Integer(new Integer(machine.get_drift().apply(Obj) * Elapsed * 50) / 1000)))))));
			machine.set_posY((posY_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(posY_tmp.apply(Obj) + new Integer(new Integer(machine.get_vel().apply(Obj) * Elapsed) / 1000)))))));

			System.out.println("UPDATE_POS executed Elapsed: " + Elapsed + " Obj: " + Obj + " ");
		}
	}

}
