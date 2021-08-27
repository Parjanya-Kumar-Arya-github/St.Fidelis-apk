package com.example.finalaboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class clock extends AppCompatActivity {
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
        setContentView(R.layout.activity_clock);
        Intent dig=new Intent(this,digital.class);
        Intent ana=new Intent(this,spaceclock.class);
        ImageView splash= findViewById(R.id.splash_clock);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.zoomin);
        ImageView digital= findViewById(R.id.digital);
        TextView digi_text= findViewById(R.id.digi_text);
        TextView ana_text= findViewById(R.id.ana_text);
        ImageView analog= findViewById(R.id.analog);
        digi_text.setClickable(true);digital.setClickable(true);ana_text.setClickable(true);analog.setClickable(true);
        digi_text.setOnClickListener(v->{
            splash.startAnimation(animation);
            new Handler().postDelayed(()->{startActivity(dig);
                        finish();}
                    ,800);
        });
        digital.setOnClickListener(v->{
            splash.startAnimation(animation);
            new Handler().postDelayed(()->{startActivity(dig);
                        finish();}
                    ,800);
        });
        ana_text.setOnClickListener(v->{
            splash.startAnimation(animation);
            new Handler().postDelayed(()->{startActivity(ana);
            finish();}
                    ,800);

        });
        analog.setOnClickListener(v->{
            splash.startAnimation(animation);
            new Handler().postDelayed(()->{startActivity(ana);
                        finish();}
                    ,800);
        });
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
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,home.class);
        startActivity(intent);
        finish();
    }
}