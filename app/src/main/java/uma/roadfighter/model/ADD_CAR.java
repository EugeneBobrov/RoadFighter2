package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class ADD_CAR{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_CAR(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Car) && NAT.instance.has(Desc) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(M) && NAT.instance.has(F)); */
	public /*@ pure */ boolean guard_ADD_CAR( Integer Car, Integer Desc, Integer H, Integer W, Integer X, Integer Y, Integer F, Integer M) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Car) && NAT.instance.has(Desc) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(M) && NAT.instance.has(F));
	}

	/*@ public normal_behavior
		requires guard_ADD_CAR(Car,Desc,H,W,X,Y,F,M);
		assignable machine.cars, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.drift, machine.vel, machine.acc, machine.maxvel, machine.finished, machine.active, machine.collision, machine.score;
		ensures guard_ADD_CAR(Car,Desc,H,W,X,Y,F,M) &&  machine.get_cars().equals(\old((machine.get_cars().union(new BSet<Integer>(Car))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Car))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,Y)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,W)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,H)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,Desc)))))) &&  machine.get_drift().equals(\old((machine.get_drift().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_vel().equals(\old((machine.get_vel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_acc().equals(\old((machine.get_acc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))) &&  machine.get_maxvel().equals(\old((machine.get_maxvel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,M)))))) &&  machine.get_finished().equals(\old((machine.get_finished().union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,true)))))) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))))) &&  machine.get_score().equals(\old((machine.get_score().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))))); 
	 also
		requires !guard_ADD_CAR(Car,Desc,H,W,X,Y,F,M);
		assignable \nothing;
		ensures true; */
	public void run_ADD_CAR( Integer Car, Integer Desc, Integer H, Integer W, Integer X, Integer Y, Integer F, Integer M){
		if(guard_ADD_CAR(Car,Desc,H,W,X,Y,F,M)) {
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

			machine.set_cars((cars_tmp.union(new BSet<Integer>(Car))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Car))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,X)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,Y)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,W)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,H)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,Desc)))));
			machine.set_drift((drift_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_vel((vel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_acc((acc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));
			machine.set_maxvel((maxvel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,M)))));
			machine.set_finished((finished_tmp.union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,true)))));
			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,false)))));
			machine.set_score((score_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,0)))));

			System.out.println("ADD_CAR executed Car: " + Car + " Desc: " + Desc + " H: " + H + " W: " + W + " X: " + X + " Y: " + Y + " F: " + F + " M: " + M + " ");
		}
	}

}
