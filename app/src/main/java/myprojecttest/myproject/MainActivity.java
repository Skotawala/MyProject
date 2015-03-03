package myprojecttest.myproject;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.media.AudioFormat;

import java.util.logging.Handler;

public class MainActivity extends Activity {
        private AudioTrack Track;

        private PowerManager.WakeLock mWakeLock;
        public TextView f;
        private recorderThread temp;


         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
           //  f = (TextView) findViewById(R.id.freq);
             temp = new recorderThread();
             PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
             mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"temp");



         }
    private Runnable readings  = new Runnable() {
        @Override
        public void run() {
            int r =temp.frequency();
            ((TextView) findViewById(R.id.freq)).setText("Value" +String.valueOf(r));
        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        if (mWakeLock.isHeld())
            mWakeLock.release();

        temp.halt();

     }
}




