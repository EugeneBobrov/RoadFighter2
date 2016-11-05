package uma.roadfighter.model;

import eventb2jml_plugin.models.JML.BRelation;
import eventb2jml_plugin.models.JML.BSet;

public class RoadFighter_Implementation extends RoadFighter_Machine {

	public BSet<Integer> OBJECTI; //@ represents OBJECTI     = OBJECT;
	public BSet<Integer> TRACKI;  //@ represents TRACKI      = TRACK;
	public Integer user_carI;     //@ represents user_carI   = user_car;
	public Integer user_trackI;   //@ represents user_trackI = user_track;

	/*@ axiom OBJECT.has(user_car); @*/
	/*@ axiom TRACK.has(user_track); @*/

	public BSet<Integer> objectsI;                //@ represents objectsI   = objects;
	public BSet<Integer> tracksI;                 //@ represents tracksI    = tracks;
	public BSet<Integer> carsI;                   //@ represents carsI      = cars;
	public BSet<Integer> obstaclesI;              //@ represents obstaclesI = obstacles;
	public BRelation<Integer,Integer> heightI;    //@ represents hegihtI    = height;
	public BRelation<Integer,Integer> widthI;     //@ represents widthI     = width;
	public BRelation<Integer,Integer> posXI;      //@ represents posXI      = posX;
	public BRelation<Integer,Integer> posYI;      //@ represents posYI      = posY;
	public BRelation<Integer,Integer> texIDI;     //@ represents texIDI     = texIDI;
	public BRelation<Integer,Integer> velI;       //@ represents velI       = vel;
	public BRelation<Integer,Integer> accI;       //@ represents accI       = acc;
	public BRelation<Integer,Integer> leanI;      //@ represents leanI      = lean;
	public BRelation<Integer,Integer> maxvelI;    //@ represents maxvelI    = maxvel;
	public BRelation<Integer,Integer> scoreI;     //@ represents scoreI     = score;
	public BRelation<Integer,Integer> frictionI;  //@ represents frictionI  = friction;
	public BRelation<Integer,Integer> bleftI;     //@ represents bleftI     = bleft;
	public BRelation<Integer,Integer> brightI;    //@ represents brightI    = bright;
	public BRelation<Integer,Integer> flineI;     //@ represents flineI     = fline;
	public BRelation<Integer,Boolean> activeI;    //@ represents activeI    = active;
	public BRelation<Integer,Boolean> collisionI; //@ represents collisionI = collision;
	public BRelation<Integer,Boolean> finishI;    //@ represents finishI    = finish;

	// Parameters
	public Integer PObj;     //@ represents PObj = Obj;
	public Integer PW;       //@ represents PW = W;
	public Integer PH;       //@ represents PH = H;
	public Integer PX;       //@ represents PX = X;
	public Integer PY;       //@ represents PY = Y;
	public Integer PTex;     //@ represents PTex = Tex;
	public Integer PTrack;   //@ represents PTrack = Track;
	public Integer PF;       //@ represents PF = F;
	public Integer PBL;      //@ represents PBL = BL;
	public Integer PBR;      //@ represents PBR = BR;
	public Integer PFL;      //@ represents PFL = FL;
	public Integer PV;       //@ represents PV = V;
	public Integer PA;       //@ represents PA = A;
	public Integer PL;       //@ represents PL = L;
	public Integer PElapsed; //@ represents PElapsed = Elapsed;
	public Integer PS;       //@ represents PS = S;
	public Integer PObj1;    //@ represents PObj1 = Obj1;
	public Integer PObj2;    //@ represents PObj1 = Obj2;


	public RoadFighter_Implementation() {
		OBJECTI = new BSet<Integer>(0,1,2,3,4,5,6,7,8,9);
		TRACKI  = new BSet<Integer>(0,1,2,3,4,5,6,7,8,9);
		user_carI = new Integer(1);
		user_trackI = new Integer(0);

		accI       = new BRelation<Integer,Integer>();
		activeI    = new BRelation<Integer,Boolean>();
		bleftI     = new BRelation<Integer,Integer>();
		brightI    = new BRelation<Integer,Integer> ();
		carsI 	   = new BSet<Integer>();
		collisionI = new BRelation<Integer,Boolean>();
		finishI    = new BRelation<Integer,Boolean>();
		flineI     = new BRelation<Integer,Integer>();
		frictionI  = new BRelation<Integer,Integer>();
		heightI    = new BRelation<Integer,Integer>();
		leanI      = new BRelation<Integer,Integer>();
		maxvelI    = new BRelation<Integer,Integer>();
		objectsI   = new BSet<Integer>();
		obstaclesI = new BSet<Integer>();
		posXI      = new BRelation<Integer,Integer>();
		posYI      = new BRelation<Integer,Integer>();
		scoreI     = new BRelation<Integer,Integer>();
		texIDI     = new BRelation<Integer,Integer>();
		tracksI    = new BSet<Integer>();
		velI       = new BRelation<Integer,Integer>();
		widthI     = new BRelation<Integer,Integer>();
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer W; (\exists Integer Tex; (\exists Integer Y; (\exists Integer H; (\exists Integer X; OBJECT.has(Obj) && !objects.has(Obj) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(Tex))))))); @*/
	public boolean guard_ADD_OBJECT() {
		return OBJECTI.has(PObj) && !objectsI.has(PObj) && PW >= 0 && PH >= 0 && PTex >= 0;
	}

	@Override
	/*@ assignable objects, width, height, texID, posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer W; (\exists Integer Tex; (\exists Integer Y; (\exists Integer H; (\exists Integer X; \old(OBJECT.has(Obj) && !objects.has(Obj) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(Tex)) &&  objects.equals(\old(objects.union((new BSet<Integer>(Obj))))) &&  width.equals(\old(width.union((new BRelation<Integer,Integer>().singleton(Obj,W))))) &&  height.equals(\old(height.union((new BRelation<Integer,Integer>().singleton(Obj,H))))) &&  texID.equals(\old(texID.union((new BRelation<Integer,Integer>().singleton(Obj,Tex))))) &&  posX.equals(\old(posX.union((new BRelation<Integer,Integer>().singleton(Obj,X))))) &&  posY.equals(\old(posY.union((new BRelation<Integer,Integer>().singleton(Obj,Y))))))))))); @*/
	public void run_ADD_OBJECT() {
		if( guard_ADD_OBJECT() ) {
			objectsI = objectsI.insert(PObj);
			widthI   = widthI.add(PObj, PW);
			heightI  = heightI.add(PObj ,PH);
			texIDI   = texIDI.add(PObj, PTex);
			posXI    = posXI.add(PObj, PX);
			posYI    = posYI.add(PObj, PY);
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer F; (\exists Integer BR; (\exists Integer FL; (\exists Integer Track; (\exists Integer BL; (TRACK.has(Track) && !tracks.has(Track) && NAT.instance.has(F) && INT.instance.has(BL) && INT.instance.has(BR) && INT.instance.has(FL) && BL < BR)))))); @*/
	public boolean guard_ADD_TRACK() {
		return TRACKI.has(PTrack) && !tracksI.has(PTrack) && PF >= 0 && PBL < PBR;
	}

	@Override
	/*@ assignable tracks, friction, bleft, bright, fline;
	 ensures (\exists Integer F; (\exists Integer BR; (\exists Integer FL; (\exists Integer Track; (\exists Integer BL; \old((TRACK.has(Track) && !tracks.has(Track) && NAT.instance.has(F) && INT.instance.has(BL) && INT.instance.has(BR) && INT.instance.has(FL) && BL < BR)) &&  tracks.equals(\old(tracks.union((new BSet<Integer>(Track))))) &&  friction.equals(\old(friction.union((new BRelation<Integer,Integer>().singleton(Track,F))))) &&  bleft.equals(\old(bleft.union((new BRelation<Integer,Integer>().singleton(Track,BL))))) &&  bright.equals(\old(bright.union((new BRelation<Integer,Integer>().singleton(Track,BR))))) &&  fline.equals(\old(fline.union((new BRelation<Integer,Integer>().singleton(Track,FL)))))))))); @*/
	public void run_ADD_TRACK() {
		if( guard_ADD_TRACK() ) {
			tracksI   = tracksI.insert(PTrack);
			frictionI = frictionI.add(PTrack, PF);
			bleftI    = bleftI.add(PTrack,PBL);
			brightI   = brightI.add(PTrack,PBR);
			flineI    = flineI.add(PTrack,PFL);
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; objects.has(Obj) && !obstacles.union(cars).has(Obj)); @*/
	public boolean guard_ADD_OBSTACLE() {
		return objectsI.has(PObj) && !obstaclesI.has(PObj) && !carsI.has(PObj);
	}

	@Override
	/*@ assignable obstacles, active;
	 ensures (\exists Integer Obj; \old(objects.has(Obj) && !obstacles.union(cars).has(Obj)) &&  obstacles.equals(\old(obstacles.union((new BSet<Integer>(Obj))))) &&  active.equals(\old(active.union((new BRelation<Integer,Boolean>().singleton(Obj,true)))))); @*/
	public void run_ADD_OBSTACLE() {
		if( guard_ADD_OBSTACLE() ) {
			obstaclesI = obstaclesI.insert(PObj);
			activeI    = activeI.add(PObj, true);
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer V; (objects.has(Obj) && !obstacles.union(cars).has(Obj) && INT.instance.has(V)))); @*/
	public boolean guard_ADD_CAR() {
		return objectsI.has(PObj) && !obstaclesI.has(PObj) && !carsI.has(PObj);
	}

	@Override
	/*@ assignable cars, vel, acc, lean, maxvel, score, collision, finish, active;
	 ensures (\exists Integer Obj; (\exists Integer V; \old((objects.has(Obj) && !obstacles.union(cars).has(Obj) && INT.instance.has(V))) &&  cars.equals(\old(cars.union((new BSet<Integer>(Obj))))) &&  vel.equals(\old(vel.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  acc.equals(\old(acc.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  lean.equals(\old(lean.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  maxvel.equals(\old(maxvel.union((new BRelation<Integer,Integer>().singleton(Obj,V))))) &&  score.equals(\old(score.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  collision.equals(\old(collision.union((new BRelation<Integer,Boolean>().singleton(Obj,false))))) &&  finish.equals(\old(finish.union((new BRelation<Integer,Boolean>().singleton(Obj,false))))) &&  active.equals(\old(active.union(true))))); @*/
	public void run_ADD_CAR() {
		if( guard_ADD_CAR() ) {
			carsI      = carsI.insert(PObj);
			velI       = velI.add(PObj, 0);
			accI       = accI.add(PObj, 0);
			leanI      = leanI.add(PObj, 0);
			maxvelI    = maxvelI.add(PObj, PV);
			scoreI     = scoreI.add(PObj, 0);
			collisionI = collisionI.add(PObj, false);
			finishI    = finishI.add(PObj, false);
			activeI    = activeI.add(PObj, true);
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Y; (\exists Integer X; (cars.has(Obj) && INT.instance.has(X) && INT.instance.has(Y))))); @*/
	public boolean guard_SET_POS() {
		return carsI.has(PObj);
	}

	@Override
	/*@ assignable posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer Y; (\exists Integer X; \old((cars.has(Obj) && INT.instance.has(X) && INT.instance.has(Y))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,X))))) &&  posY.equals(\old(posY.override((new BRelation<Integer,Integer>().singleton(Obj,Y)))))))); @*/
	public void run_SET_POS() {
		if( guard_SET_POS() ) {
			posXI = posXI.override(new BRelation<Integer,Integer>().singleton(PObj, PX));
			posYI = posYI.override(new BRelation<Integer,Integer>().singleton(PObj, PY));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer V; (cars.has(Obj) && INT.instance.has(V) && V <= maxvel.apply(Obj)))); @*/
	public boolean guard_SET_VEL() {
		return carsI.has(PObj) && PV <= maxvelI.apply(PObj);
	}

	@Override
	/*@ assignable vel;
	 ensures (\exists Integer Obj; (\exists Integer V; \old((cars.has(Obj) && INT.instance.has(V) && V <= maxvel.apply(Obj))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,V))))))); @*/
	public void run_SET_VEL() {
		if( guard_SET_VEL() ) {
			velI = velI.override(new BRelation<Integer,Integer>().singleton(PObj, PV));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer A; (cars.has(Obj) && INT.instance.has(A)))); @*/
	public boolean guard_SET_ACC() {
		return carsI.has(PObj);
	}

	@Override
	/*@ assignable acc;
	 ensures (\exists Integer Obj; (\exists Integer A; \old((cars.has(Obj) && INT.instance.has(A))) &&  acc.equals(\old(acc.override((new BRelation<Integer,Integer>().singleton(Obj,A))))))); @*/
	public void run_SET_ACC() {
		if( guard_SET_ACC() ) {
			accI = accI.override(new BRelation<Integer,Integer>().singleton(PObj, PA));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer L; (cars.has(Obj) && (new BSet<Integer>(-1,0,1)).has(L)))); @*/
	public boolean guard_SET_LEAN() {
		return carsI.has(PObj) && ( PL.equals(0) || PL.equals(1) || PL.equals(-1) );
	}

	@Override
	/*@ assignable lean;
	 ensures (\exists Integer Obj; (\exists Integer L; \old((cars.has(Obj) && (new BSet<Integer>(-1,0,1)).has(L))) &&  lean.equals(\old(lean.override((new BRelation<Integer,Integer>().singleton(Obj,L))))))); @*/
	public void run_SET_LEAN() {
		if( guard_SET_LEAN() ) {
			leanI = leanI.override(new BRelation<Integer,Integer>().singleton(PObj, PL));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (cars.has(Obj) && NAT.instance.has(Elapsed)))); @*/
	public boolean guard_UPDATE_POS() {
		return carsI.has(PObj) && PElapsed >= 0;
	}

	@Override
	/*@ assignable posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer Elapsed; \old((cars.has(Obj) && NAT.instance.has(Elapsed))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,posX.apply(Obj) + lean.apply(Obj) * Elapsed * 50 / 1000))))) &&  posY.equals(\old(posY.override((new BRelation<Integer,Integer>().singleton(Obj,posY.apply(Obj) + vel.apply(Obj) * Elapsed / 1000))))))); @*/
	public void run_UPDATE_POS() {
		if( guard_UPDATE_POS() ) {
			Integer X = posXI.apply(PObj) + leanI.apply(PObj) * PElapsed * 50 / 1000;
			Integer Y = posYI.apply(PObj) + velI.apply(PObj) * PElapsed / 1000;
			posXI = posXI.override(new BRelation<Integer,Integer>().singleton(PObj, X));
			posYI = posYI.override(new BRelation<Integer,Integer>().singleton(PObj, Y));
		}
	}
	
	@Override
	/*@	 assignable \nothing;
	ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (cars.has(Obj) && NAT.instance.has(Elapsed)))); @*/
	public boolean guard_UPDATE_VEL() {
		return carsI.has(PObj) && PElapsed >= 0;
	}

	@Override
	/*@	 assignable vel;
	ensures (\exists Integer Obj; (\exists Integer Elapsed; \old((cars.has(Obj) && NAT.instance.has(Elapsed))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,vel.apply(Obj) + acc.apply(Obj) + Elapsed / 1000))))))); @*/
	public void run_UPDATE_VEL() {
		if( guard_UPDATE_VEL() ) {
			Integer V = velI.apply(PObj) + accI.apply(PObj) * PElapsed / 1000;
			velI = velI.override(new BRelation<Integer,Integer>().singleton(PObj, V));
		}
	}

	@Override
	/*@	 assignable \nothing;
	ensures \result <==> (\exists Integer Obj; (cars.has(Obj) && vel.apply(Obj) > maxvel.apply(Obj))); @*/
	public boolean guard_SET_MAXVEL() {
		return carsI.has(PObj) && velI.apply(PObj) > maxvelI.apply(PObj);
	}

	@Override
	/*@	 assignable vel;
	ensures (\exists Integer Obj; \old((cars.has(Obj) && vel.apply(Obj) > maxvel.apply(Obj))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,maxvel.apply(Obj))))))); @*/
	public void run_SET_MAXVEL() {
		if( guard_SET_MAXVEL()) {
			velI = velI.override(new BRelation<Integer,Integer>().singleton(PObj, maxvelI.apply(PObj)));
		}
	}

	@Override
	/*@	 assignable \nothing;
	ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (\exists Integer Track; (cars.has(Obj) && NAT.instance.has(Elapsed) && tracks.has(Track) && acc.apply(Obj) == 0)))); @*/
	public boolean guard_APPLY_FRICTION() {
		return carsI.has(PObj) && tracksI.has(PTrack) && accI.apply(PObj).equals(0) && PElapsed >= 0;
	}

	@Override
	/*@	 assignable vel;
	ensures (\exists Integer Obj; (\exists Integer Elapsed; (\exists Integer Track; \old((cars.has(Obj) && NAT.instance.has(Elapsed) && tracks.has(Track) && acc.apply(Obj) == 0)) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,vel.apply(Obj) - friction.apply(Track) * 5 * Elapsed)))))))); @*/
	public void run_APPLY_FRICTION() {
		if( guard_APPLY_FRICTION() ) {
			Integer V = velI.apply(PObj) - (frictionI.apply(PTrack) * 5 * PElapsed) / 1000;
			velI = velI.override(new BRelation<Integer,Integer>().singleton(PObj, V));
		}
	}

	@Override
	/*@	 assignable \nothing;
	ensures \result <==> (\exists Integer Obj; (cars.has(Obj) && vel.apply(Obj) < 0)); @*/
	public boolean guard_SET_ZEROVEL() {
		return carsI.has(PObj) && velI.apply(PObj) < 0;
	}

	@Override
	/*@	 assignable vel;
	ensures (\exists Integer Obj; \old((cars.has(Obj) && vel.apply(Obj) < 0)) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,0)))))); @*/
	public void run_SET_ZEROVEL() {
		if( guard_SET_ZEROVEL() ) {
			velI = velI.override(new BRelation<Integer,Integer>().singleton(PObj, 0));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer S; (cars.has(Obj) && INT.instance.has(S)))); @*/
	public boolean guard_UPDATE_SCORE() {
		return carsI.has(PObj);
	}

	@Override
	/*@ assignable score;
	 ensures (\exists Integer Obj; (\exists Integer S; \old((cars.has(Obj) && INT.instance.has(S))) &&  score.equals(\old(score.override((new BRelation<Integer,Integer>().singleton(Obj,score.apply(Obj) + S))))))); @*/
	public void run_UPDATE_SCORE() {
		if( guard_UPDATE_SCORE() ) {
			scoreI = scoreI.override(new BRelation<Integer,Integer>().singleton(PObj, scoreI.apply(PObj) + PS));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
	public boolean guard_CAR_RESET() {
		return carsI.has(PObj) && tracksI.has(PTrack);
	}

	@Override
	/*@ assignable posX, vel, acc, lean;
	 ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,bright.apply(Track) - bleft.apply(Track) / 2))))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  acc.equals(\old(acc.override((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  lean.equals(\old(lean.override((new BRelation<Integer,Integer>().singleton(Obj,0))))))); @*/
	public void run_CAR_RESET() {
		if( guard_CAR_RESET() ) {
			Integer X = bleftI.apply(PTrack) + (brightI.apply(PTrack) - bleftI.apply(PTrack)) / 2;
			velI  = velI.override(new BRelation<Integer,Integer>().singleton(PObj, 0));
			accI  = accI.override(new BRelation<Integer,Integer>().singleton(PObj, 0));
			leanI = leanI.override(new BRelation<Integer,Integer>().singleton(PObj, 0));
			posXI = posXI.override(new BRelation<Integer,Integer>().singleton(PObj, X));
			collisionI = collisionI.override(new BRelation<Integer,Boolean>().singleton(PObj, false));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
	public boolean guard_FINISH_TRACK() {
		return carsI.has(PObj) && tracksI.has(PTrack);
	}

	@Override
	/*@ assignable finish;
	 ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  finish.equals(\old(finish.override((new BRelation<Integer,Boolean>().singleton(Obj,fline.apply(PTrack) < posY.apply(PObj)))))))); @*/
	public void run_FINISH_TRACK() {
		if( guard_FINISH_TRACK() ) {
			Boolean res = flineI.apply(PTrack) < posYI.apply(PObj);
			finishI = finishI.override(new BRelation<Integer,Boolean>().singleton(PObj, res));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
	public boolean guard_TRACK_COLLISION() {
		return carsI.has(PObj) && tracksI.has(PTrack);
	}

	@Override
	/*@ assignable collision;
	 ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  collision.equals(\old(collision.override((new BRelation<Integer,Boolean>().singleton(Obj,(posX.apply(Obj) - width.apply(Obj)/2 < bleft.apply(Track)) || (posX.apply(Obj) + width.apply(Obj)/2 > bright.apply(Track))))))))); @*/
	public void run_TRACK_COLLISION() {
		if( guard_TRACK_COLLISION() ) {
			Boolean res = (posXI.apply(PObj) - widthI.apply(PObj)/2 < bleftI.apply(PTrack)) || (posXI.apply(PObj) + widthI.apply(PObj)/2 > brightI.apply(PTrack));
			collisionI = collisionI.override(new BRelation<Integer,Boolean>().singleton(PObj, res));
		}
	}

	@Override
	/*@ assignable \nothing;
	 ensures \result <==> (\exists Integer Obj1; (\exists Integer Obj2; (obstacles.union(cars).has(Obj1) && cars.has(Obj2)))); @*/
	public boolean guard_OBJECT_COLLISION() {
		return carsI.has(PObj2) && (carsI.has(PObj2) || obstaclesI.has(PObj1));
	}

	@Override
	/*@ assignable collision, active;
	 ensures (\exists Integer Obj1; (\exists Integer Obj2;
	 \old((obstacles.union(cars).has(Obj1) &&
	 cars.has(Obj2))) &&
	 collision.equals(\old(collision.override((new BRelation<Integer,Boolean>().singleton(Obj2,
	    Math.abs( posX.apply(Obj1) - posX.apply(Obj2) ) < width.apply(Obj1)/2 + width.apply(Obj2)/2 &&
	    Math.abs( posY.apply(Obj1) - posY.apply(Obj2) ) < ( height.apply(Obj1)/2 + height.apply(Obj2)/2 )))))) &&
	 active.equals(\old(active.override((new BRelation<Integer,Boolean>().singleton(Obj1,
	    !(Math.abs( posX.apply(Obj1) - posX.apply(Obj2) ) < width.apply(Obj1)/2 + width.apply(Obj2)/2 &&
	      Math.abs( posY.apply(Obj1) - posY.apply(Obj2) ) < height.apply(Obj1)/2 + height.apply(Obj2)/2 )))))))); @*/
	public void run_OBJECT_COLLISION() {
		if( guard_OBJECT_COLLISION() ) {
			Boolean res = Math.abs( posXI.apply(PObj1) - posXI.apply(PObj2) ) < widthI.apply(PObj1)/2 + widthI.apply(PObj2)/2 &&
			              Math.abs( posYI.apply(PObj1) - posYI.apply(PObj2) ) < heightI.apply(PObj1)/2 + heightI.apply(PObj2)/2;
			collisionI = collisionI.override(new BRelation<Integer,Boolean>().singleton(PObj2, res));
			activeI    = activeI.override(new BRelation<Integer,Boolean>().singleton(PObj1, !res));
		}	
	}
}
