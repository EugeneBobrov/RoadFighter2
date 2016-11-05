package uma.roadfighter.model;

import eventb2jml_plugin.models.JML.*;
import org.jmlspecs.models.JMLEqualsEqualsPair; 

public abstract class RoadFighter_Machine{
/*@ public model BSet<Integer> OBJECT; @*/
/*@ public model BSet<Integer> TRACK; @*/
/*@ public ghost static final Integer user_car; @*/
/*@ public ghost static final Integer user_track; @*/
/*@ axiom OBJECT.has(user_car); @*/
/*@ axiom TRACK.has(user_track); @*/

/*@  public model BRelation<Integer,Integer> acc;
	 public model BRelation<Integer,Boolean> active;
	 public model BRelation<Integer,Integer> bleft;
	 public model BRelation<Integer,Integer> bright;
	 public model BSet<Integer> cars;
	 public model BRelation<Integer,Boolean> collision;
	 public model BRelation<Integer,Boolean> finish;
	 public model BRelation<Integer,Integer> fline;
	 public model BRelation<Integer,Integer> friction;
	 public model BRelation<Integer,Integer> height;
	 public model BRelation<Integer,Integer> lean;
	 public model BRelation<Integer,Integer> maxvel;
	 public model BSet<Integer> objects;
	 public model BSet<Integer> obstacles;
	 public model BRelation<Integer,Integer> posX;
	 public model BRelation<Integer,Integer> posY;
	 public model BRelation<Integer,Integer> score;
	 public model BRelation<Integer,Integer> texID;
	 public model BSet<Integer> tracks;
	 public model BRelation<Integer,Integer> vel;
	 public model BRelation<Integer,Integer> width;
@*/
/*@  public invariant
	 objects.isSubset(OBJECT) && tracks.isSubset(TRACK) &&
	 obstacles.isSubset(objects) && cars.isSubset(objects) &&
	 obstacles.intersection(cars).equals(BSet.EMPTY) &&
	 width.isaFunction() && width.domain().equals(objects) && width.range().isSubset(NAT.instance) &&
	 height.isaFunction() && height.domain().equals(objects) && height.range().isSubset(NAT.instance) &&
	 texID.isaFunction() && texID.domain().equals(objects) && texID.range().isSubset(NAT.instance) &&
	 posX.isaFunction() && posX.domain().equals(objects) && posX.range().isSubset(INT.instance) &&
	 posY.isaFunction() && posY.domain().equals(objects) && posY.range().isSubset(INT.instance) &&
	 vel.isaFunction() && vel.domain().equals(cars) && vel.range().isSubset(INT.instance) &&
	 acc.isaFunction() && acc.domain().equals(cars) && acc.range().isSubset(INT.instance) &&
	 Integer.isaFunction() && Integer.domain().equals(cars) && Integer.range().isSubset((new BSet<Integer>(-1,0,1))).has(lean) &&
	 maxvel.isaFunction() && maxvel.domain().equals(cars) && maxvel.range().isSubset(INT.instance) &&
	 friction.isaFunction() && friction.domain().equals(tracks) && friction.range().isSubset(NAT.instance) &&
	 bleft.isaFunction() && bleft.domain().equals(tracks) && bleft.range().isSubset(INT.instance) &&
	 bright.isaFunction() && bright.domain().equals(tracks) && bright.range().isSubset(INT.instance) &&
	 fline.isaFunction() && fline.domain().equals(tracks) && fline.range().isSubset(INT.instance) &&
	 score.isaFunction() && score.domain().equals(cars) && score.range().isSubset(INT.instance) &&
	 collision.isaFunction() && collision.domain().equals(cars) && collision.range().isSubset(BOOL.instance) &&
	 finish.isaFunction() && finish.domain().equals(cars) && finish.range().isSubset(BOOL.instance) &&
	 active.isaFunction() && active.domain().equals(obstacles.union(cars)) && active.range().isSubset(BOOL.instance); @*/


/*@ initially 
	objects.isEmpty() &&
	tracks.isEmpty() &&
	obstacles.isEmpty() &&
	cars.isEmpty() &&
	width.isEmpty() &&
	height.isEmpty() &&
	texID.isEmpty() &&
	posX.isEmpty() &&
	posY.isEmpty() &&
	vel.isEmpty() &&
	acc.isEmpty() &&
	lean.isEmpty() &&
	maxvel.isEmpty() &&
	friction.isEmpty() &&
	bleft.isEmpty() &&
	bright.isEmpty() &&
	fline.isEmpty() &&
	score.isEmpty() &&
	collision.isEmpty() &&
	finish.isEmpty() &&
	active.isEmpty() ; @*/ 

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer W; (\exists Integer Tex; (\exists Integer Y; (\exists Integer H; (\exists Integer X; OBJECT.has(Obj) && !objects.has(Obj) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(Tex))))))); @*/
public abstract boolean guard_ADD_OBJECT();

/*@	 assignable objects, width, height, texID, posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer W; (\exists Integer Tex; (\exists Integer Y; (\exists Integer H; (\exists Integer X; \old(OBJECT.has(Obj) && !objects.has(Obj) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(Tex)) &&  objects.equals(\old(objects.union((new BSet<Integer>(Obj))))) &&  width.equals(\old(width.union((new BRelation<Integer,Integer>().singleton(Obj,W))))) &&  height.equals(\old(height.union((new BRelation<Integer,Integer>().singleton(Obj,H))))) &&  texID.equals(\old(texID.union((new BRelation<Integer,Integer>().singleton(Obj,Tex))))) &&  posX.equals(\old(posX.union((new BRelation<Integer,Integer>().singleton(Obj,X))))) &&  posY.equals(\old(posY.union((new BRelation<Integer,Integer>().singleton(Obj,Y))))))))))); @*/
public abstract void run_ADD_OBJECT();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer F; (\exists Integer BR; (\exists Integer FL; (\exists Integer Track; (\exists Integer BL; (TRACK.has(Track) && !tracks.has(Track) && NAT.instance.has(F) && INT.instance.has(BL) && INT.instance.has(BR) && INT.instance.has(FL) && BL < BR)))))); @*/
public abstract boolean guard_ADD_TRACK();

/*@	 assignable tracks, friction, bleft, bright, fline;
	 ensures (\exists Integer F; (\exists Integer BR; (\exists Integer FL; (\exists Integer Track; (\exists Integer BL; \old((TRACK.has(Track) && !tracks.has(Track) && NAT.instance.has(F) && INT.instance.has(BL) && INT.instance.has(BR) && INT.instance.has(FL) && BL < BR)) &&  tracks.equals(\old(tracks.union((new BSet<Integer>(Track))))) &&  friction.equals(\old(friction.union((new BRelation<Integer,Integer>().singleton(Track,F))))) &&  bleft.equals(\old(bleft.union((new BRelation<Integer,Integer>().singleton(Track,BL))))) &&  bright.equals(\old(bright.union((new BRelation<Integer,Integer>().singleton(Track,BR))))) &&  fline.equals(\old(fline.union((new BRelation<Integer,Integer>().singleton(Track,FL)))))))))); @*/
public abstract void run_ADD_TRACK();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; objects.has(Obj) && !obstacles.union(cars).has(Obj)); @*/
public abstract boolean guard_ADD_OBSTACLE();

/*@	 assignable obstacles, active;
	 ensures (\exists Integer Obj; \old(objects.has(Obj) && !obstacles.union(cars).has(Obj)) &&  obstacles.equals(\old(obstacles.union((new BSet<Integer>(Obj))))) &&  active.equals(\old(active.union((new BRelation<Integer,Boolean>().singleton(Obj,true)))))); @*/
public abstract void run_ADD_OBSTACLE();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer V; (objects.has(Obj) && !obstacles.union(cars).has(Obj) && INT.instance.has(V)))); @*/
public abstract boolean guard_ADD_CAR();

/*@	 assignable cars, vel, acc, lean, maxvel, score, collision, finish, active;
	 ensures (\exists Integer Obj; (\exists Integer V; \old((objects.has(Obj) && !obstacles.union(cars).has(Obj) && INT.instance.has(V))) &&  cars.equals(\old(cars.union((new BSet<Integer>(Obj))))) &&  vel.equals(\old(vel.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  acc.equals(\old(acc.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  lean.equals(\old(lean.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  maxvel.equals(\old(maxvel.union((new BRelation<Integer,Integer>().singleton(Obj,V))))) &&  score.equals(\old(score.union((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  collision.equals(\old(collision.union((new BRelation<Integer,Boolean>().singleton(Obj,false))))) &&  finish.equals(\old(finish.union((new BRelation<Integer,Boolean>().singleton(Obj,false))))) &&  active.equals(\old(active.union(true))))); @*/
public abstract void run_ADD_CAR();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Y; (\exists Integer X; (cars.has(Obj) && INT.instance.has(X) && INT.instance.has(Y))))); @*/
public abstract boolean guard_SET_POS();

/*@	 assignable posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer Y; (\exists Integer X; \old((cars.has(Obj) && INT.instance.has(X) && INT.instance.has(Y))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,X))))) &&  posY.equals(\old(posY.override((new BRelation<Integer,Integer>().singleton(Obj,Y)))))))); @*/
public abstract void run_SET_POS();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer V; (cars.has(Obj) && INT.instance.has(V) && V <= maxvel.apply(Obj)))); @*/
public abstract boolean guard_SET_VEL();

/*@	 assignable vel;
	 ensures (\exists Integer Obj; (\exists Integer V; \old((cars.has(Obj) && INT.instance.has(V) && V <= maxvel.apply(Obj))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,V))))))); @*/
public abstract void run_SET_VEL();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer A; (cars.has(Obj) && INT.instance.has(A)))); @*/
public abstract boolean guard_SET_ACC();

/*@	 assignable acc;
	 ensures (\exists Integer Obj; (\exists Integer A; \old((cars.has(Obj) && INT.instance.has(A))) &&  acc.equals(\old(acc.override((new BRelation<Integer,Integer>().singleton(Obj,A))))))); @*/
public abstract void run_SET_ACC();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer L; (cars.has(Obj) && (new BSet<Integer>(-1,0,1)).has(L)))); @*/
public abstract boolean guard_SET_LEAN();

/*@	 assignable lean;
	 ensures (\exists Integer Obj; (\exists Integer L; \old((cars.has(Obj) && (new BSet<Integer>(-1,0,1)).has(L))) &&  lean.equals(\old(lean.override((new BRelation<Integer,Integer>().singleton(Obj,L))))))); @*/
public abstract void run_SET_LEAN();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (cars.has(Obj) && NAT.instance.has(Elapsed)))); @*/
public abstract boolean guard_UPDATE_POS();

/*@	 assignable posX, posY;
	 ensures (\exists Integer Obj; (\exists Integer Elapsed; \old((cars.has(Obj) && NAT.instance.has(Elapsed))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,posX.apply(Obj) + lean.apply(Obj) * Elapsed * 50 / 1000))))) &&  posY.equals(\old(posY.override((new BRelation<Integer,Integer>().singleton(Obj,posY.apply(Obj) + vel.apply(Obj) * Elapsed / 1000))))))); @*/
public abstract void run_UPDATE_POS();

/*@	 assignable \nothing;
ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (cars.has(Obj) && NAT.instance.has(Elapsed)))); @*/
public abstract boolean guard_UPDATE_VEL();

/*@	 assignable vel;
ensures (\exists Integer Obj; (\exists Integer Elapsed; \old((cars.has(Obj) && NAT.instance.has(Elapsed))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,vel.apply(Obj) + acc.apply(Obj) + Elapsed / 1000))))))); @*/
public abstract void run_UPDATE_VEL();

/*@	 assignable \nothing;
ensures \result <==> (\exists Integer Obj; (cars.has(Obj) && vel.apply(Obj) > maxvel.apply(Obj))); @*/
public abstract boolean guard_SET_MAXVEL();

/*@	 assignable vel;
ensures (\exists Integer Obj; \old((cars.has(Obj) && vel.apply(Obj) > maxvel.apply(Obj))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,maxvel.apply(Obj))))))); @*/
public abstract void run_SET_MAXVEL();

/*@	 assignable \nothing;
ensures \result <==> (\exists Integer Obj; (\exists Integer Elapsed; (\exists Integer Track; (cars.has(Obj) && NAT.instance.has(Elapsed) && tracks.has(Track) && acc.apply(Obj) == 0)))); @*/
public abstract boolean guard_APPLY_FRICTION();

/*@	 assignable vel;
ensures (\exists Integer Obj; (\exists Integer Elapsed; (\exists Integer Track; \old((cars.has(Obj) && NAT.instance.has(Elapsed) && tracks.has(Track) && acc.apply(Obj) == 0)) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,vel.apply(Obj) - friction.apply(Track) * 5 * Elapsed)))))))); @*/
public abstract void run_APPLY_FRICTION();

/*@	 assignable \nothing;
ensures \result <==> (\exists Integer Obj; (cars.has(Obj) && vel.apply(Obj) < 0)); @*/
public abstract boolean guard_SET_ZEROVEL();

/*@	 assignable vel;
ensures (\exists Integer Obj; \old((cars.has(Obj) && vel.apply(Obj) < 0)) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,0)))))); @*/
public abstract void run_SET_ZEROVEL();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer S; (cars.has(Obj) && INT.instance.has(S)))); @*/
public abstract boolean guard_UPDATE_SCORE();

/*@	 assignable score;
	 ensures (\exists Integer Obj; (\exists Integer S; \old((cars.has(Obj) && INT.instance.has(S))) &&  score.equals(\old(score.override((new BRelation<Integer,Integer>().singleton(Obj,score.apply(Obj) + S))))))); @*/
public abstract void run_UPDATE_SCORE();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
public abstract boolean guard_CAR_RESET();

/*@	 assignable posX, vel, acc, lean, collision;
ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  posX.equals(\old(posX.override((new BRelation<Integer,Integer>().singleton(Obj,bright.apply(Track) - bleft.apply(Track) / 2))))) &&  vel.equals(\old(vel.override((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  acc.equals(\old(acc.override((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  lean.equals(\old(lean.override((new BRelation<Integer,Integer>().singleton(Obj,0))))) &&  collision.equals(\old(collision.override(false))))))); @*/
public abstract void run_CAR_RESET();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
public abstract boolean guard_FINISH_TRACK();

/*@	 assignable finish;
	 ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  finish.equals(\old(finish.override((new BRelation<Integer,Boolean>().singleton(Obj,fline.apply(PTrack) < posY.apply(PObj)))))))); @*/
public abstract void run_FINISH_TRACK();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj; (\exists Integer Track; (cars.has(Obj) && tracks.has(Track)))); @*/
public abstract boolean guard_TRACK_COLLISION();

/*@	 assignable collision;
	 ensures (\exists Integer Obj; (\exists Integer Track; \old((cars.has(Obj) && tracks.has(Track))) &&  collision.equals(\old(collision.override((new BRelation<Integer,Boolean>().singleton(Obj,(posX.apply(Obj) - width.apply(Obj)/2 < bleft.apply(Track)) || (posX.apply(Obj) + width.apply(Obj)/2 > bright.apply(Track))))))))); @*/
public abstract void run_TRACK_COLLISION();

/*@	 assignable \nothing;
	 ensures \result <==> (\exists Integer Obj1; (\exists Integer Obj2; (obstacles.union(cars).has(Obj1) && cars.has(Obj2)))); @*/
public abstract boolean guard_OBJECT_COLLISION();

/*@	 assignable collision, active;
	 ensures (\exists Integer Obj1; (\exists Integer Obj2; \old((obstacles.union(cars).has(Obj1) && cars.has(Obj2))) &&  collision.equals(\old(collision.override((new BRelation<Integer,Boolean>().singleton(Obj2,Math.abs( posX.apply(Obj1) - posX.apply(Obj2) ) < width.apply(Obj1)/2 + width.apply(Obj2)/2 && Math.abs( posY.apply(Obj1) - posY.apply(Obj2) ) < ( height.apply(Obj1)/2 + height.apply(Obj2)/2 )))))) && active.equals(\old(active.override((new BRelation<Integer,Boolean>().singleton(Obj1,Math.abs( posX.apply(Obj1) - posX.apply(Obj2) ) < width.apply(Obj1)/2 + width.apply(Obj2)/2 && !(Math.abs( posY.apply(Obj1) - posY.apply(Obj2) ) < ( height.apply(Obj1)/2 + height.apply(Obj2)/2 ))))))))); @*/
public abstract void run_OBJECT_COLLISION();


}
