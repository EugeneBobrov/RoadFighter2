package uma.roadfighter.view;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Bundle;

import uma.roadfighter.R;



public class RoadFighterActivity extends Activity {
    /** Called when the activity is first created. */
	public GLSurfaceView GLView;

    BackgroundSound mBackgroundSound = new BackgroundSound();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*final int MAX_STREAMS = 1;
        final SoundPool sp;
        final int sndMain;

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sndMain = sp.load(this, R.raw.overdue, 1);
        sp.play(sndMain, 1, 1, 0, 0, 1);
*/


/*
        MediaPlayer mediaPlayer;

        mediaPlayer = MediaPlayer.create(this, R.raw.overdue);

        //AudioManager am;
        mediaPlayer.setLooping(true);
        //am = (AudioManager) getSystemService(AUDIO_SERVICE);

        mediaPlayer.start();

*/
        // OpenGL surface
        GLView = new RoadFighterGLView(this);
        setContentView(GLView);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	GLView.onPause();
        //mBackgroundSound.cancel(true);

    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	GLView.onResume();
        mBackgroundSound.execute();
    }


    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(RoadFighterActivity.this, R.raw.rfmusic2);
            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }

    }

}