package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_VEL{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_VEL(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && NAT.instance.has(Elapsed) && (new Integer(machine.get_vel().apply(Car) + machine.get_acc().apply(Car) + new Integer(Elapsed / new Integer(1000)))).compareTo(new Integer(0)) >= 0); */
	public /*@ pure */ boolean guard_UPDATE_VEL( Integer Car, Integer Elapsed) {
		return (machine.get_cars().has(Car) && NAT.instance.has(Elapsed) && (new Integer(machine.get_vel().apply(Car) + machine.get_acc().apply(Car) + new Integer(Elapsed / new Integer(1000)))).compareTo(new Integer(0)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_UPDATE_VEL(Car,Elapsed);
		assignable machine.vel;
		ensures guard_UPDATE_VEL(Car,Elapsed) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(machine.get_vel().apply(Car) + machine.get_acc().apply(Car) + new Integer(Elapsed / 1000)))))))); 
	 also
		requires !guard_UPDATE_VEL(Car,Elapsed);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_VEL( Integer Car, Integer Elapsed){
		if(guard_UPDATE_VEL(Car,Elapsed)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(vel_tmp.apply(Car) + machine.get_acc().apply(Car) + new Integer(Elapsed / 1000)))))));

			System.out.println("UPDATE_VEL executed Car: " + Car + " Elapsed: " + Elapsed + " ");
		}
	}

}
