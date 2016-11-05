package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class OBJECT_COLLISION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public OBJECT_COLLISION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> ((machine.get_obstacles().union(machine.get_cars())).has(Obj) && machine.get_cars().has(MyCar)); */
	public /*@ pure */ boolean guard_OBJECT_COLLISION( Integer MyCar, Integer Obj) {
		return ((machine.get_obstacles().union(machine.get_cars())).has(Obj) && machine.get_cars().has(MyCar));
	}

	/*@ public normal_behavior
		requires guard_OBJECT_COLLISION(MyCar,Obj);
		assignable machine.collision, machine.active;
		ensures guard_OBJECT_COLLISION(MyCar,Obj) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(MyCar,((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(MyCar) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(MyCar) / 2))) < 0))))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj,(!((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(MyCar) / 2))) < 0) && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(MyCar) / 2))) < 0))))))); 
	 also
		requires !guard_OBJECT_COLLISION(MyCar,Obj);
		assignable \nothing;
		ensures true; */
	public void run_OBJECT_COLLISION( Integer MyCar, Integer Obj){
		if(guard_OBJECT_COLLISION(MyCar,Obj)) {
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(MyCar,((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(MyCar) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(MyCar) / 2))) < 0))))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj,(!((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(MyCar) / 2))) < 0) && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(MyCar))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(MyCar) / 2))) < 0))))));

			System.out.println("OBJECT_COLLISION executed MyCar: " + MyCar + " Obj: " + Obj + " ");
		}
	}

}
