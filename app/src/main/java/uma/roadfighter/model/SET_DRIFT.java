package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_DRIFT{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_DRIFT(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D)); */
	public /*@ pure */ boolean guard_SET_DRIFT( Integer Car, Integer D) {
		return (machine.get_cars().has(Car) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D));
	}

	/*@ public normal_behavior
		requires guard_SET_DRIFT(Car,D);
		assignable machine.drift;
		ensures guard_SET_DRIFT(Car,D) &&  machine.get_drift().equals(\old((machine.get_drift().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,D)))))); 
	 also
		requires !guard_SET_DRIFT(Car,D);
		assignable \nothing;
		ensures true; */
	public void run_SET_DRIFT( Integer Car, Integer D){
		if(guard_SET_DRIFT(Car,D)) {
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();

			machine.set_drift((drift_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,D)))));

			System.out.println("SET_DRIFT executed Car: " + Car + " D: " + D + " ");
		}
	}

}
