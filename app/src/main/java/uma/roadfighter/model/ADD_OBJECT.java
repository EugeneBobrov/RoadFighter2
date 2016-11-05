package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class ADD_OBJECT{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_OBJECT(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Obj) && NAT.instance.has(Desc) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y)); */
	public /*@ pure */ boolean guard_ADD_OBJECT( Integer Desc, Integer H, Integer Obj, Integer W, Integer X, Integer Y) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Obj) && NAT.instance.has(Desc) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y));
	}

	/*@ public normal_behavior
		requires guard_ADD_OBJECT(Desc,H,Obj,W,X,Y);
		assignable machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc;
		ensures guard_ADD_OBJECT(Desc,H,Obj,W,X,Y) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Obj))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,W)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,H)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Desc)))))); 
	 also
		requires !guard_ADD_OBJECT(Desc,H,Obj,W,X,Y);
		assignable \nothing;
		ensures true; */
	public void run_ADD_OBJECT( Integer Desc, Integer H, Integer Obj, Integer W, Integer X, Integer Y){
		if(guard_ADD_OBJECT(Desc,H,Obj,W,X,Y)) {
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();

			machine.set_objects((objects_tmp.union(new BSet<Integer>(Obj))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,W)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,H)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Desc)))));

			System.out.println("ADD_OBJECT executed Desc: " + Desc + " H: " + H + " Obj: " + Obj + " W: " + W + " X: " + X + " Y: " + Y + " ");
		}
	}

}
