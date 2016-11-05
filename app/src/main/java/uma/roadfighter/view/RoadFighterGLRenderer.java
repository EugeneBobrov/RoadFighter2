package uma.roadfighter.view;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import uma.roadfighter.R;

import eventb2jml_plugin.models.JML.BRelation;

import uma.roadfighter.model.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.SystemClock;

public class RoadFighterGLRenderer implements GLSurfaceView.Renderer {
	// Game
	RoadFighter_Implementation RoadFighter = new RoadFighter_Implementation();
	// User Car
	int carLow, carHigh;
	// Obstacles and Opponents
	int nObstacles, nOpponents;
	// Random Generator
	Random generator;
	
	// Dialog
	AlertDialog.Builder scoreDialog;
	Boolean dialogShown;
	float totalTime;
	
	// Context
	private Context context;
	// Buffers
	private FloatBuffer vertexBuffer;
	private FloatBuffer texCoordBuffer;
	// Time
	private long currentTime, lastTime, elapsed;
	// ID's
	private int[] texID;
	// Events
	public char event;
	
	RoadFighterGLRenderer( Context c ) {
		super();
		context = c;
		generator = new Random();
		scoreDialog = new AlertDialog.Builder(c);
	}
	
	private float NormalizeCoordinate(int c) {
		return ((float)c/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI)) * 2.0f;
	}
	
	public void onSurfaceCreated( GL10 gl, EGLConfig config ) {
		// score Dialog
		scoreDialog.setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialogShown = false;
		totalTime = 0;
		
		// Circuit(0)
		RoadFighter.PObj = RoadFighter.user_trackI; RoadFighter.PTex = R.drawable.track; RoadFighter.PW = 100; RoadFighter.PH = 400; RoadFighter.PX = 0; RoadFighter.PY = 0;
		RoadFighter.run_ADD_OBJECT();
		RoadFighter.PTrack = RoadFighter.user_trackI; RoadFighter.PF = 3; RoadFighter.PBL = 25; RoadFighter.PBR = 75; RoadFighter.PFL = 380;
		RoadFighter.run_ADD_TRACK();
		
		// User Car(1)
		RoadFighter.PObj = RoadFighter.user_carI; RoadFighter.PTex = R.drawable.car; RoadFighter.PW = 15; RoadFighter.PH = 15; RoadFighter.PX = RoadFighter.widthI.apply(RoadFighter.user_trackI)/2; RoadFighter.PY = 50;
		RoadFighter.run_ADD_OBJECT();
		RoadFighter.PV = 50; carLow = 20; carHigh = 40;
		RoadFighter.run_ADD_CAR();
		RoadFighter.PS = 100000;
		RoadFighter.run_UPDATE_SCORE();
				
		// Obstacles
		nObstacles = 3;
		RoadFighter.PTex = R.drawable.obstacle; RoadFighter.PW = 10; RoadFighter.PH = 10; 
		for( int i = 2; i < 2 + nObstacles; i++ ) {
			RoadFighter.PObj = i;
			RoadFighter.PX = RoadFighter.bleftI.apply(RoadFighter.user_trackI) + RoadFighter.PW/2 + generator.nextInt( RoadFighter.brightI.apply(RoadFighter.user_trackI) - RoadFighter.bleftI.apply(RoadFighter.user_trackI) - RoadFighter.PW );
			RoadFighter.PY = RoadFighter.PH/2 + generator.nextInt( RoadFighter.flineI.apply(RoadFighter.user_trackI) - RoadFighter.PH );
			RoadFighter.run_ADD_OBJECT();
			RoadFighter.run_ADD_OBSTACLE();
		}
		
		// Opponents
		nOpponents = 3;
		RoadFighter.PTex = R.drawable.opponent; RoadFighter.PW = 15; RoadFighter.PH = 15;
		for( int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++ ) {
			RoadFighter.PObj = i;
			RoadFighter.PX = RoadFighter.bleftI.apply(RoadFighter.user_trackI) + RoadFighter.PW/2 + generator.nextInt( RoadFighter.brightI.apply(RoadFighter.user_trackI) - RoadFighter.bleftI.apply(RoadFighter.user_trackI) - RoadFighter.PW );
			RoadFighter.PY = RoadFighter.PH/2 + generator.nextInt( RoadFighter.flineI.apply(RoadFighter.user_trackI) - RoadFighter.PH );
			RoadFighter.run_ADD_OBJECT();
			RoadFighter.PV = 38;
			RoadFighter.run_ADD_CAR();
			RoadFighter.PA = carHigh;
			RoadFighter.run_SET_ACC();
		}
		
		// Time
		lastTime = SystemClock.uptimeMillis();
		
		// OpenGL
		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		gl.glEnable( GL10.GL_TEXTURE_2D );
		gl.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
		
		// Vertex Buffer
		float vertex[] = { 
				-1.0f, -1.0f, 0.0f,
				-1.0f,  1.0f, 0.0f,
				 1.0f,  1.0f, 0.0f,
				-1.0f, -1.0f, 0.0f,
				 1.0f,  1.0f, 0.0f,
				 1.0f, -1.0f, 0.0f
		};
		// Buffer with space
		ByteBuffer vb = ByteBuffer.allocateDirect( vertex.length * 4 ); // Space
		vb.order( ByteOrder.nativeOrder() );	// Hardware order
		vertexBuffer = vb.asFloatBuffer();  	// Memory to VertexBuffer (in float)
		vertexBuffer.put( vertex );				// Put vertex info
		vertexBuffer.position( 0 );

		// TexCoord Buffer
		float texCoord[] = { 
				0.0f, 1.0f,
				0.0f, 0.0f,
				1.0f, 0.0f,
				0.0f, 1.0f,
				1.0f, 0.0f,
				1.0f, 1.0f
		};
		// Buffer with space
		ByteBuffer tb = ByteBuffer.allocateDirect( texCoord.length * 4 ); // Space
		tb.order( ByteOrder.nativeOrder() );	// Hardware order
		texCoordBuffer = tb.asFloatBuffer();  	// Memory to VertexBuffer (in float)
		texCoordBuffer.put( texCoord );			// Put vertex info
		texCoordBuffer.position( 0 );
		
		// Textures
		texID = new int[4];
		gl.glGenTextures( 4, texID, 0 );
		
		// Track texture
		gl.glBindTexture( GL10.GL_TEXTURE_2D, texID[0] );
		InputStream is = context.getResources().openRawResource( +R.drawable.track );
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
		bitmap.recycle();
		
		// User-car texture
		gl.glBindTexture( GL10.GL_TEXTURE_2D, texID[1] );
		is = context.getResources().openRawResource( +R.drawable.car  );
		bitmap = BitmapFactory.decodeStream(is);
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
		bitmap.recycle();
		
		// Opponent-car texture
		gl.glBindTexture( GL10.GL_TEXTURE_2D, texID[2] );
		is = context.getResources().openRawResource( +R.drawable.opponent );
		bitmap = BitmapFactory.decodeStream(is);
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
		bitmap.recycle();
		
		// Obstacle texture
		gl.glBindTexture( GL10.GL_TEXTURE_2D, texID[3] );
		is = context.getResources().openRawResource( +R.drawable.obstacle );
		bitmap = BitmapFactory.decodeStream(is);
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
		bitmap.recycle();
	}
	
	public void onDrawFrame( GL10 gl ) {
		// Time
		currentTime = SystemClock.uptimeMillis();
		elapsed     = currentTime - lastTime;
		lastTime    = currentTime;
		
		// User finished
		if( RoadFighter.finishI.apply(RoadFighter.user_carI) ){
			event = 'n';
			if( !dialogShown ) {
				((Activity)context).runOnUiThread( new Runnable() {
					public void run() {
						scoreDialog.setMessage("Score: " + RoadFighter.scoreI.apply(1));
						scoreDialog.create();
						AlertDialog alert = scoreDialog.show();
						alert.show();
					}
				});
				dialogShown = true;
			}
		}
		
		// SCORE
		RoadFighter.PObj = RoadFighter.user_carI;
		RoadFighter.PS = -(int)elapsed;
		RoadFighter.run_UPDATE_SCORE();
		
		// Input - Logic
		// Lean and Acceleration
		switch( event ) {
		case 'l':
			RoadFighter.PL = -1;
			RoadFighter.run_SET_LEAN();
			break;
		case 'r':
			RoadFighter.PL = 1;
			RoadFighter.run_SET_LEAN();
			break;
		case 'c':
			RoadFighter.PL = 0;
			RoadFighter.run_SET_LEAN();
			break;
		case 'a':
			RoadFighter.PA = carLow;
			RoadFighter.run_SET_ACC();
			break;
		case 'h':
			RoadFighter.PA = carHigh;
			RoadFighter.run_SET_ACC();
			break;
		case 'n':
			RoadFighter.PA = 0;
			RoadFighter.run_SET_ACC();
		}
		
		/* Update Cars */
		RoadFighter.PElapsed = (int)elapsed + 10;
		
		// User Car
		RoadFighter.run_UPDATE_VEL();
		RoadFighter.run_SET_MAXVEL();
		RoadFighter.run_APPLY_FRICTION();
		RoadFighter.run_SET_ZEROVEL();
		RoadFighter.run_UPDATE_POS();
		
		// Opponents
		for( int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++ ) {
			if( RoadFighter.activeI.apply(i) ) {
				RoadFighter.PObj = i;
				RoadFighter.run_UPDATE_VEL();
				RoadFighter.run_SET_MAXVEL();
				RoadFighter.run_APPLY_FRICTION();
				RoadFighter.run_SET_ZEROVEL();
				RoadFighter.run_UPDATE_POS();
			}
		}
		
		/* Collisions */
		RoadFighter.PObj2 = RoadFighter.user_carI;
		// Obstacles + Opponents
		for( int i = 2; i < 2 + nObstacles + nOpponents; i++ ) {
			if( RoadFighter.activeI.apply(i) ) {
				RoadFighter.PObj1 = i;
				RoadFighter.run_OBJECT_COLLISION();
			}
			// Reset Car
			if( RoadFighter.collisionI.apply(RoadFighter.user_carI) ) {
				RoadFighter.PObj = RoadFighter.user_carI;
				RoadFighter.PTrack = RoadFighter.user_trackI;
				RoadFighter.run_CAR_RESET();
				RoadFighter.PS = -5000;
				RoadFighter.run_UPDATE_SCORE();
			}
		}
		
		// Track
		RoadFighter.PObj = RoadFighter.user_carI;	
		RoadFighter.PTrack = RoadFighter.user_trackI;
		RoadFighter.run_TRACK_COLLISION();
		// Reset Car
		if( RoadFighter.collisionI.apply(RoadFighter.user_carI) ) {
			RoadFighter.run_CAR_RESET();
			RoadFighter.PS = -1000;
			RoadFighter.run_UPDATE_SCORE();
		}
		
		// Finish track?
		RoadFighter.run_FINISH_TRACK();
		
		// Clear
		gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
		
		// Buffers
		gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, vertexBuffer );
		gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, texCoordBuffer );
		
		// Camera
		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		GLU.gluLookAt( 
				gl,
				1.0f, NormalizeCoordinate( RoadFighter.posYI.apply(RoadFighter.user_carI) ) + 0.5f, 1.0f,
				1.0f, NormalizeCoordinate( RoadFighter.posYI.apply(RoadFighter.user_carI) ) + 0.5f, 0.0f,
				0.0f, 1.0f, 0.0f );
		
		// Track
		gl.glPushMatrix();
		gl.glScalef( 1.0f, (float)RoadFighter.heightI.apply(RoadFighter.user_trackI)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), 1.0f );
		gl.glTranslatef(1.0f, 1.0f, 0.0f );
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[0] );
		gl.glDrawArrays( GL10.GL_TRIANGLES, 0, 6 );
		gl.glPopMatrix();
		
		// User-car
		gl.glPushMatrix();
		gl.glTranslatef( NormalizeCoordinate( RoadFighter.posXI.apply(RoadFighter.user_carI) ), NormalizeCoordinate( RoadFighter.posYI.apply(RoadFighter.user_carI) ), 0.0f );
		gl.glScalef( (float)RoadFighter.widthI.apply(RoadFighter.user_carI)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), (float)RoadFighter.heightI.apply(RoadFighter.user_carI)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), 1.0f );
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[1] );
		gl.glEnable(GL10.GL_BLEND );
		gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
		gl.glDrawArrays( GL10.GL_TRIANGLES, 0, 6 );
		gl.glDisable( GL10.GL_BLEND );
		gl.glPopMatrix();
		
		// Obstacles
		for( int i = 2; i < 2 + nObstacles; i++ ) {
			if( RoadFighter.activeI.apply(i) ) {
				gl.glPushMatrix();
				gl.glTranslatef( NormalizeCoordinate( RoadFighter.posXI.apply(i) ), NormalizeCoordinate( RoadFighter.posYI.apply(i) ), 0.0f );
				gl.glScalef( (float)RoadFighter.widthI.apply(i)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), (float)RoadFighter.heightI.apply(i)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), 1.0f );
				gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[3] );
				gl.glEnable(GL10.GL_BLEND );
				gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
				gl.glDrawArrays( GL10.GL_TRIANGLES, 0, 6 );
				gl.glDisable( GL10.GL_BLEND );
				gl.glPopMatrix();
			}
		}
		
		// Opponents
		for( int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++ ) {
			if( RoadFighter.activeI.apply(i) ) {
				gl.glPushMatrix();
				gl.glTranslatef( NormalizeCoordinate( RoadFighter.posXI.apply(i) ), NormalizeCoordinate(  RoadFighter.posYI.apply(i) ), 0.0f );
				gl.glScalef( (float)RoadFighter.widthI.apply(i)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), (float)RoadFighter.heightI.apply(i)/(float)RoadFighter.widthI.apply(RoadFighter.user_trackI), 1.0f );
				gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[2] );
				gl.glEnable(GL10.GL_BLEND );
				gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
				gl.glDrawArrays( GL10.GL_TRIANGLES, 0, 6 );
				gl.glDisable( GL10.GL_BLEND );
				gl.glPopMatrix();
			}
		}
	}
	
	public void onSurfaceChanged( GL10 gl, int width, int height ) {
		gl.glViewport( 0, 0, width, height );
	}

}
