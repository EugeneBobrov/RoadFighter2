package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class WALL_COLLISION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public WALL_COLLISION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car) && machine.get_lanes().has(Lane)); */
	public /*@ pure */ boolean guard_WALL_COLLISION( Integer Car, Integer Lane) {
		return (machine.get_cars().has(Car) && machine.get_lanes().has(Lane));
	}

	/*@ public normal_behavior
		requires guard_WALL_COLLISION(Car,Lane);
		assignable machine.collision;
		ensures guard_WALL_COLLISION(Car,Lane) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,(!((machine.get_left_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Car) - new Integer(machine.get_width().apply(Car) / 2))) <= 0 && (machine.get_right_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Car) + new Integer(machine.get_width().apply(Car) / 2))) >= 0)))))))); 
	 also
		requires !guard_WALL_COLLISION(Car,Lane);
		assignable \nothing;
		ensures true; */
	public void run_WALL_COLLISION( Integer Car, Integer Lane){
		if(guard_WALL_COLLISION(Car,Lane)) {
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();

			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car,(!((machine.get_left_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Car) - new Integer(machine.get_width().apply(Car) / 2))) <= 0 && (machine.get_right_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Car) + new Integer(machine.get_width().apply(Car) / 2))) >= 0)))))));

			System.out.println("WALL_COLLISION executed Car: " + Car + " Lane: " + Lane + " ");
		}
	}

}
