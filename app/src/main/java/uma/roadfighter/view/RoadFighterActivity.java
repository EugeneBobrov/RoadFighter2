package uma.roadfighter.view;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class RoadFighterActivity extends Activity {
    /** Called when the activity is first created. */
	public GLSurfaceView GLView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // OpenGL surface
        GLView = new RoadFighterGLView(this);
        setContentView(GLView);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	GLView.onPause();
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	GLView.onResume();
    }
}