package com.example.finalaboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        TextView textView;
        private Handler handler=new Handler();
        int executed=3;
        Animation animation;
        ImageView splash;
        Context context;
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
            setContentView(R.layout.activity_main);
            context=this;
            textView=findViewById(R.id.loading);
            handler.postDelayed(runnable,750);
            splash=findViewById(R.id.splash_home);
            animation= AnimationUtils.loadAnimation(this,R.anim.zoomin);
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
        Runnable splashRun=new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(context,home.class);
                startActivity(intent);
                finish();
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Loading...".substring(0, 11 - executed));
                if (executed > 1) {
                    executed--;
                    handler.postDelayed(runnable, 750);
                }
                else{
                    splash.startAnimation(animation);
                    handler.postDelayed(splashRun,800);
                }

            }

        };


}
