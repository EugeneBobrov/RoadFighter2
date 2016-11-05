package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class CAR_RESET{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public CAR_RESET(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && machine.get_lanes().has(Lane)); */
	public /*@ pure */ boolean guard_CAR_RESET( Integer Car, Integer Lane) {
		return (machine.get_cars().has(Car) && machine.get_lanes().has(Lane));
	}

	/*@ public normal_behavior
		requires guard_CAR_RESET(Car,Lane);
		assignable machine.posX, machine.drift, machine.vel, machine.acc, machine.collision;
		ensures guard_CAR_RESET(Car,Lane) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(new Integer(machine.get_right_border().apply(Lane) - machine.get_left_border().apply(Lane)) / 2))))))) &&  machine.get_drift().equals(\old((machine.get_drift().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_acc().equals(\old((machine.get_acc().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))))); 
	 also
		requires !guard_CAR_RESET(Car,Lane);
		assignable \nothing;
		ensures true; */
	public void run_CAR_RESET( Integer Car, Integer Lane){
		if(guard_CAR_RESET(Car,Lane)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(new Integer(machine.get_right_border().apply(Lane) - machine.get_left_border().apply(Lane)) / 2))))));
			machine.set_drift((drift_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_acc((acc_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))));

			System.out.println("CAR_RESET executed Car: " + Car + " Lane: " + Lane + " ");
		}
	}

}
