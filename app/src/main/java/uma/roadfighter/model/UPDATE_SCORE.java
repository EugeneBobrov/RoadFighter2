package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_SCORE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_SCORE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && NAT.instance.has(S)); */
	public /*@ pure */ boolean guard_UPDATE_SCORE( Integer Car, Integer S) {
		return (machine.get_cars().has(Car) && NAT.instance.has(S));
	}

	/*@ public normal_behavior
		requires guard_UPDATE_SCORE(Car,S);
		assignable machine.score;
		ensures guard_UPDATE_SCORE(Car,S) &&  machine.get_score().equals(\old((machine.get_score().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(machine.get_score().apply(Car) + S))))))); 
	 also
		requires !guard_UPDATE_SCORE(Car,S);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_SCORE( Integer Car, Integer S){
		if(guard_UPDATE_SCORE(Car,S)) {
			BRelation<Integer,Integer> score_tmp = machine.get_score();

			machine.set_score((score_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car,new Integer(score_tmp.apply(Car) + S))))));

			System.out.println("UPDATE_SCORE executed Car: " + Car + " S: " + S + " ");
		}
	}

}
