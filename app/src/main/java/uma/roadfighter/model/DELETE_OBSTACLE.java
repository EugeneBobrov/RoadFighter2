package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class DELETE_OBSTACLE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public DELETE_OBSTACLE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.get_obstacles().has(Obs); */
	public /*@ pure */ boolean guard_DELETE_OBSTACLE( Integer Obs) {
		return machine.get_obstacles().has(Obs);
	}

	/*@ public normal_behavior
		requires guard_DELETE_OBSTACLE(Obs);
		assignable machine.obstacles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.active;
		ensures guard_DELETE_OBSTACLE(Obs) &&  machine.get_obstacles().equals(\old(machine.get_obstacles().difference(new BSet<Integer>(Obs)))) &&  machine.get_objects().equals(\old(machine.get_objects().difference(new BSet<Integer>(Obs)))) &&  machine.get_posX().equals(\old(machine.get_posX().domainSubtraction(new BSet<Integer>(Obs)))) &&  machine.get_posY().equals(\old(machine.get_posY().domainSubtraction(new BSet<Integer>(Obs)))) &&  machine.get_width().equals(\old(machine.get_width().domainSubtraction(new BSet<Integer>(Obs)))) &&  machine.get_height().equals(\old(machine.get_height().domainSubtraction(new BSet<Integer>(Obs)))) &&  machine.get_obj_desc().equals(\old(machine.get_obj_desc().domainSubtraction(new BSet<Integer>(Obs)))) &&  machine.get_active().equals(\old(machine.get_active().domainSubtraction(new BSet<Integer>(Obs)))); 
	 also
		requires !guard_DELETE_OBSTACLE(Obs);
		assignable \nothing;
		ensures true; */
	public void run_DELETE_OBSTACLE( Integer Obs){
		if(guard_DELETE_OBSTACLE(Obs)) {
			BSet<Integer> obstacles_tmp = machine.get_obstacles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_obstacles(obstacles_tmp.difference(new BSet<Integer>(Obs)));
			machine.set_objects(objects_tmp.difference(new BSet<Integer>(Obs)));
			machine.set_posX(posX_tmp.domainSubtraction(new BSet<Integer>(Obs)));
			machine.set_posY(posY_tmp.domainSubtraction(new BSet<Integer>(Obs)));
			machine.set_width(width_tmp.domainSubtraction(new BSet<Integer>(Obs)));
			machine.set_height(height_tmp.domainSubtraction(new BSet<Integer>(Obs)));
			machine.set_obj_desc(obj_desc_tmp.domainSubtraction(new BSet<Integer>(Obs)));
			machine.set_active(active_tmp.domainSubtraction(new BSet<Integer>(Obs)));

			System.out.println("DELETE_OBSTACLE executed Obs: " + Obs + " ");
		}
	}

}
