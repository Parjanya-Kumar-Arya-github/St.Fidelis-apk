package com.example.finalaboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Date;

public class spaceclock extends AppCompatActivity {

    private Handler mHandler = new Handler();
    ImageView second_hand,minute_hand,hour_hand;
    Context context;
    MediaPlayer mediaPlayer;
    public int timeWait=0,checked=0,rotationAngle_for_sec=6,mCurrRotation_for_sec= 0,rotationAngle_for_min=1,
            mCurrRotation_for_min= 0,rotationAngle_for_hr=1,mCurrRotation_for_hr= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceclock);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVol, 0);
        mediaPlayer=MediaPlayer.create(this,R.raw.clock_sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        Date date=new Date();
        mCurrRotation_for_sec=date.getSeconds()*6-6;
        mCurrRotation_for_min=date.getMinutes()*6-1;
        mCurrRotation_for_hr=date.getHours()*30-1+(int)(date.getMinutes()*0.5f);
        context=this;
        second_hand=findViewById(R.id.spaceclock_second);minute_hand=findViewById(R.id.spaceclock_minute);hour_hand=findViewById(R.id.spaceclock_hour);
        timeWait=date.getSeconds()*1000;
        second.run();
        minute.run();
        hour.run();


    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    private Runnable second = new Runnable() {
        @Override
        public void run() {

            float fromRotation = mCurrRotation_for_sec;
            float toRotation = mCurrRotation_for_sec += rotationAngle_for_sec;

            Animation animation = new RotateAnimation(
                    fromRotation,
                    toRotation,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.9f);
            animation.setFillAfter(true);
            animation.setDuration(1);
            second_hand.startAnimation(animation);
            mHandler.postDelayed(this, 999);
        }
    };
    private Runnable minute = new Runnable() {
        @Override
        public void run() {
            timeWait=(checked==0)?timeWait:0;
            checked=1;
            float fromRotation = mCurrRotation_for_min;
            float toRotation = mCurrRotation_for_min += rotationAngle_for_min;

            Animation animation = new RotateAnimation(
                    fromRotation,
                    toRotation,
                    Animation.RELATIVE_TO_SELF, 0.6f, Animation.RELATIVE_TO_SELF, 0.75f);
            animation.setFillAfter(true);
            animation.setDuration(1);
            minute_hand.startAnimation(animation);
            mHandler.postDelayed(this,9999-timeWait);
        }
    };
    private Runnable hour = new Runnable() {
        @Override
        public void run() {
            float fromRotation = mCurrRotation_for_hr;
            float toRotation = mCurrRotation_for_hr += rotationAngle_for_hr;
            Animation animation = new RotateAnimation(
                    fromRotation,
                    toRotation,
                    Animation.RELATIVE_TO_SELF, 0.45f, Animation.RELATIVE_TO_SELF, 0.9f);
            animation.setFillAfter(true);
            animation.setDuration(1);
            hour_hand.startAnimation(animation);
            mHandler.postDelayed(this, 599999);
        }
    };
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,clock.class);
        mediaPlayer.stop();
        startActivity(intent);
        finish();

    }
}