package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_ACC{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_ACC(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && NAT.instance.has(A)); */
	public /*@ pure */ boolean guard_SET_ACC( Integer A, Integer Car) {
		return (machine.get_cars().has(Car) && NAT.instance.has(A));
	}

	/*@ public normal_behavior
		requires guard_SET_ACC(A,Car);
		assignable machine.acc;
		ensures guard_SET_ACC(A,Car) &&  machine.get_acc().equals(\old((machine.get_acc().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,A)))))); 
	 also
		requires !guard_SET_ACC(A,Car);
		assignable \nothing;
		ensures true; */
	public void run_SET_ACC( Integer A, Integer Car){
		if(guard_SET_ACC(A,Car)) {
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();

			machine.set_acc((acc_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,A)))));

			System.out.println("SET_ACC executed A: " + A + " Car: " + Car + " ");
		}
	}

}
