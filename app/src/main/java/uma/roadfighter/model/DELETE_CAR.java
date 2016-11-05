package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class DELETE_CAR{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public DELETE_CAR(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.get_cars().has(Car); */
	public /*@ pure */ boolean guard_DELETE_CAR( Integer Car) {
		return machine.get_cars().has(Car);
	}

	/*@ public normal_behavior
		requires guard_DELETE_CAR(Car);
		assignable machine.cars, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.drift, machine.vel, machine.acc, machine.maxvel, machine.finished, machine.active, machine.collision, machine.score;
		ensures guard_DELETE_CAR(Car) &&  machine.get_cars().equals(\old(machine.get_cars().difference(new BSet<Integer>(Car)))) &&  machine.get_objects().equals(\old(machine.get_objects().difference(new BSet<Integer>(Car)))) &&  machine.get_posX().equals(\old(machine.get_posX().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_posY().equals(\old(machine.get_posY().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_width().equals(\old(machine.get_width().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_height().equals(\old(machine.get_height().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_obj_desc().equals(\old(machine.get_obj_desc().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_drift().equals(\old(machine.get_drift().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_vel().equals(\old(machine.get_vel().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_acc().equals(\old(machine.get_acc().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_maxvel().equals(\old(machine.get_maxvel().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_finished().equals(\old(machine.get_finished().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_active().equals(\old(machine.get_active().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_collision().equals(\old(machine.get_collision().domainSubtraction(new BSet<Integer>(Car)))) &&  machine.get_score().equals(\old(machine.get_score().domainSubtraction(new BSet<Integer>(Car)))); 
	 also
		requires !guard_DELETE_CAR(Car);
		assignable \nothing;
		ensures true; */
	public void run_DELETE_CAR( Integer Car){
		if(guard_DELETE_CAR(Car)) {
			BSet<Integer> cars_tmp = machine.get_cars();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();
			BRelation<Integer,Integer> maxvel_tmp = machine.get_maxvel();
			BRelation<Integer,Boolean> finished_tmp = machine.get_finished();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();
			BRelation<Integer,Integer> score_tmp = machine.get_score();

			machine.set_cars(cars_tmp.difference(new BSet<Integer>(Car)));
			machine.set_objects(objects_tmp.difference(new BSet<Integer>(Car)));
			machine.set_posX(posX_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_posY(posY_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_width(width_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_height(height_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_obj_desc(obj_desc_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_drift(drift_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_vel(vel_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_acc(acc_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_maxvel(maxvel_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_finished(finished_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_active(active_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_collision(collision_tmp.domainSubtraction(new BSet<Integer>(Car)));
			machine.set_score(score_tmp.domainSubtraction(new BSet<Integer>(Car)));

			System.out.println("DELETE_CAR executed Car: " + Car + " ");
		}
	}

}
