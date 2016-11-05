package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class ADD_OBSTACLE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_OBSTACLE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Obs) && NAT.instance.has(Desc) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(W) && NAT.instance.has(H)); */
	public /*@ pure */ boolean guard_ADD_OBSTACLE( Integer Desc, Integer H, Integer Obs, Integer W, Integer X, Integer Y) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Obs) && NAT.instance.has(Desc) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(W) && NAT.instance.has(H));
	}

	/*@ public normal_behavior
		requires guard_ADD_OBSTACLE(Desc,H,Obs,W,X,Y);
		assignable machine.obstacles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.active;
		ensures guard_ADD_OBSTACLE(Desc,H,Obs,W,X,Y) &&  machine.get_obstacles().equals(\old((machine.get_obstacles().union(new BSet<Integer>(Obs))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Obs))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Y)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,W)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,H)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Desc)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs,true)))))); 
	 also
		requires !guard_ADD_OBSTACLE(Desc,H,Obs,W,X,Y);
		assignable \nothing;
		ensures true; */
	public void run_ADD_OBSTACLE( Integer Desc, Integer H, Integer Obs, Integer W, Integer X, Integer Y){
		if(guard_ADD_OBSTACLE(Desc,H,Obs,W,X,Y)) {
			BSet<Integer> obstacles_tmp = machine.get_obstacles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_obstacles((obstacles_tmp.union(new BSet<Integer>(Obs))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Obs))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,X)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Y)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,W)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,H)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Desc)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs,true)))));

			System.out.println("ADD_OBSTACLE executed Desc: " + Desc + " H: " + H + " Obs: " + Obs + " W: " + W + " X: " + X + " Y: " + Y + " ");
		}
	}

}
