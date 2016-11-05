package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_POS{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_POS(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_objects().has(Obj) && INT.instance.has(X) && INT.instance.has(Y)); */
	public /*@ pure */ boolean guard_SET_POS( Integer Obj, Integer X, Integer Y) {
		return (machine.get_objects().has(Obj) && INT.instance.has(X) && INT.instance.has(Y));
	}

	/*@ public normal_behavior
		requires guard_SET_POS(Obj,X,Y);
		assignable machine.posX, machine.posY;
		ensures guard_SET_POS(Obj,X,Y) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))))); 
	 also
		requires !guard_SET_POS(Obj,X,Y);
		assignable \nothing;
		ensures true; */
	public void run_SET_POS( Integer Obj, Integer X, Integer Y){
		if(guard_SET_POS(Obj,X,Y)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))));
			machine.set_posY((posY_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))));

			System.out.println("SET_POS executed Obj: " + Obj + " X: " + X + " Y: " + Y + " ");
		}
	}

}
