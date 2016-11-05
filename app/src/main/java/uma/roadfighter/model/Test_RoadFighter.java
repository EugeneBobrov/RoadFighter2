package uma.roadfighter.model;
import java.util.Random;
import Util.Utilities;
import eventb_prelude.*;

public class Test_RoadFighter{

	public static Integer random_USER_LANE;
	public static Integer random_USER_CAR;

	static Random rnd = new Random();
	static Integer max_size_BSet = 10;
	Integer min_integer = Utilities.min_integer;
	Integer max_integer = Utilities.max_integer;

	public Integer GenerateRandomInteger(){
		BSet<Integer> S =  new BSet<Integer>(
				new Enumerated(min_integer, max_integer)
				);
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return (Integer) S.toArray()[rnd.nextInt(S.size())];
	}

	public boolean GenerateRandomBoolean(){
		boolean res = (Boolean) BOOL.instance.toArray()[rnd.nextInt(2)];

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}


	public BSet<Integer> GenerateRandomIntegerBSet(){
		int size = rnd.nextInt(max_size_BSet);
		BSet<Integer> S = new BSet<Integer>();
		while (S.size() != size){
			S.add(GenerateRandomInteger());
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return S;
	}

	public BSet<Boolean> GenerateRandomBooleanBSet(){
		int size = rnd.nextInt(2);
		BSet<Boolean> res = new BSet<Boolean>();
		if (size == 0){
			res = new BSet<Boolean>(GenerateRandomBoolean());
		}else{
			res = new BSet<Boolean>(true,false);
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

	public BRelation<Integer,Integer> GenerateRandomBRelation(){
		BRelation<Integer,Integer> res = new BRelation<Integer,Integer>();
		int size = rnd.nextInt(max_size_BSet);
		while (res.size() != size){
			res.add(
					new Pair<Integer, Integer>(GenerateRandomInteger(), GenerateRandomInteger()));
		}
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

	public static void main(String[] args){
		Test_RoadFighter test = new Test_RoadFighter();

		/** User defined code that reflects axioms and theorems: Begin **/
		test.random_USER_LANE = test.GenerateRandomInteger();
		test.random_USER_CAR = test.GenerateRandomInteger();
		/** User defined code that reflects axioms and theorems: End **/

		RoadFighter machine = new RoadFighter();
		int n = 24; //the number of events in the machine
		//Init values for parameters in event: SET_LEFT_BORDER
		Integer B = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_RIGHT_BORDER
		Integer B = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_LANE
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_FINISH_LINE
		Integer F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_POS
		Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_LANE
		Integer Finish = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Left = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Right = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_OBJECT
		Integer Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_CAR
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer M = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_CAR
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_OBSTACLE
		Integer Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obs = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_OBSTACLE
		Integer Obs = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_VEL
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer V = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_ACC
		Integer A = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_MAXVEL
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer M = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_VEL
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: APPLY_FRICTION
		Integer Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: CAR_RESET
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: WALL_COLLISION
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: OBJECT_COLLISION
		Integer MyCar = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: FINISHED_LANE
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_POS
		Integer Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_SCORE
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer S = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_DRIFT
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer D = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_ZERO_VEL
		Integer Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		while (true){
			switch (rnd.nextInt(n)){
			case 0: if (machine.evt_SET_LEFT_BORDER.guard_SET_LEFT_BORDER(B,Lane))
				machine.evt_SET_LEFT_BORDER.run_SET_LEFT_BORDER(B,Lane); break;
			case 1: if (machine.evt_SET_RIGHT_BORDER.guard_SET_RIGHT_BORDER(B,Lane))
				machine.evt_SET_RIGHT_BORDER.run_SET_RIGHT_BORDER(B,Lane); break;
			case 2: if (machine.evt_DELETE_LANE.guard_DELETE_LANE(Lane))
				machine.evt_DELETE_LANE.run_DELETE_LANE(Lane); break;
			case 3: if (machine.evt_SET_FINISH_LINE.guard_SET_FINISH_LINE(F,Lane))
				machine.evt_SET_FINISH_LINE.run_SET_FINISH_LINE(F,Lane); break;
			case 4: if (machine.evt_SET_POS.guard_SET_POS(Obj,X,Y))
				machine.evt_SET_POS.run_SET_POS(Obj,X,Y); break;
			case 5: if (machine.evt_ADD_LANE.guard_ADD_LANE(Finish,Lane,Left,Right,F))
				machine.evt_ADD_LANE.run_ADD_LANE(Finish,Lane,Left,Right,F); break;
			case 6: if (machine.evt_ADD_OBJECT.guard_ADD_OBJECT(Desc,H,Obj,W,X,Y))
				machine.evt_ADD_OBJECT.run_ADD_OBJECT(Desc,H,Obj,W,X,Y); break;
			case 7: if (machine.evt_ADD_CAR.guard_ADD_CAR(Car,Desc,H,W,X,Y,F,M))
				machine.evt_ADD_CAR.run_ADD_CAR(Car,Desc,H,W,X,Y,F,M); break;
			case 8: if (machine.evt_DELETE_CAR.guard_DELETE_CAR(Car))
				machine.evt_DELETE_CAR.run_DELETE_CAR(Car); break;
			case 9: if (machine.evt_ADD_OBSTACLE.guard_ADD_OBSTACLE(Desc,H,Obs,W,X,Y))
				machine.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(Desc,H,Obs,W,X,Y); break;
			case 10: if (machine.evt_DELETE_OBSTACLE.guard_DELETE_OBSTACLE(Obs))
				machine.evt_DELETE_OBSTACLE.run_DELETE_OBSTACLE(Obs); break;
			case 11: if (machine.evt_SET_VEL.guard_SET_VEL(Car,V))
				machine.evt_SET_VEL.run_SET_VEL(Car,V); break;
			case 12: if (machine.evt_SET_ACC.guard_SET_ACC(A,Car))
				machine.evt_SET_ACC.run_SET_ACC(A,Car); break;
			case 13: if (machine.evt_SET_MAXVEL.guard_SET_MAXVEL(Car,M))
				machine.evt_SET_MAXVEL.run_SET_MAXVEL(Car,M); break;
			case 14: if (machine.evt_UPDATE_VEL.guard_UPDATE_VEL(Car,Elapsed))
				machine.evt_UPDATE_VEL.run_UPDATE_VEL(Car,Elapsed); break;
			case 15: if (machine.evt_APPLY_FRICTION.guard_APPLY_FRICTION(Elapsed,Lane,Obj))
				machine.evt_APPLY_FRICTION.run_APPLY_FRICTION(Elapsed,Lane,Obj); break;
			case 16: if (machine.evt_CAR_RESET.guard_CAR_RESET(Car,Lane))
				machine.evt_CAR_RESET.run_CAR_RESET(Car,Lane); break;
			case 17: if (machine.evt_WALL_COLLISION.guard_WALL_COLLISION(Car,Lane))
				machine.evt_WALL_COLLISION.run_WALL_COLLISION(Car,Lane); break;
			case 18: if (machine.evt_OBJECT_COLLISION.guard_OBJECT_COLLISION(MyCar,Obj))
				machine.evt_OBJECT_COLLISION.run_OBJECT_COLLISION(MyCar,Obj); break;
			case 19: if (machine.evt_FINISHED_LANE.guard_FINISHED_LANE(Car,Lane))
				machine.evt_FINISHED_LANE.run_FINISHED_LANE(Car,Lane); break;
			case 20: if (machine.evt_UPDATE_POS.guard_UPDATE_POS(Elapsed,Obj))
				machine.evt_UPDATE_POS.run_UPDATE_POS(Elapsed,Obj); break;
			case 21: if (machine.evt_UPDATE_SCORE.guard_UPDATE_SCORE(Car,S))
				machine.evt_UPDATE_SCORE.run_UPDATE_SCORE(Car,S); break;
			case 22: if (machine.evt_SET_DRIFT.guard_SET_DRIFT(Car,D))
				machine.evt_SET_DRIFT.run_SET_DRIFT(Car,D); break;
			case 23: if (machine.evt_SET_ZERO_VEL.guard_SET_ZERO_VEL(Car))
				machine.evt_SET_ZERO_VEL.run_SET_ZERO_VEL(Car); break;
			}
			B = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			B = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Finish = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Left = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Right = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			M = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obs = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obs = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			V = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			A = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			M = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			MyCar = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			S = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			D = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		}
	}

}
