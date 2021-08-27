package com.example.finalaboutme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    protected long pressedTime;
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
        setContentView(R.layout.activity_home);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.righttoleft);
        Animation animation_bounce = AnimationUtils.loadAnimation(this, R.anim.about_me_animation);
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        TextView about = findViewById(R.id.about_text);
        TextView clock_text = findViewById(R.id.clock_gallery_text);
        Intent intent = new Intent(this, about.class);
        about.startAnimation(animation_bounce);
        ImageView spaceship = findViewById(R.id.spaceship);
        ImageView about_me = findViewById(R.id.about_me);
        ImageView clock_gall = findViewById(R.id.clock_gallery);
        ImageView stars = findViewById(R.id.stars);
        spaceship.startAnimation(animation);
        stars.startAnimation(animation2);
        about_me.startAnimation(animation_bounce);
        Typeface orbitron = ResourcesCompat.getFont(this,R.font.orbitron);
        TextClock textClock=findViewById(R.id.time_home);
        textClock.setTypeface(orbitron);
        clock_gall.startAnimation(bounce);
        clock_text.startAnimation(bounce);
        about_me.setClickable(true);
        about_me.setOnClickListener(v -> {
            Animation animation_for_spaceship = AnimationUtils.loadAnimation(this, R.anim.spaceshipspecial);
            spaceship.startAnimation(animation_for_spaceship);
            Animation animation1=AnimationUtils.loadAnimation(this,R.anim.zoomin);
            ImageView splash=findViewById(R.id.splash);
            animation1.setDuration(500);
            new Handler().postDelayed(()->{
                splash.startAnimation(animation1);
            },1000);
            new Handler().postDelayed(() -> {
                spaceship.setImageResource(0);
                startActivity(intent);
                finish();
            }, 1500);
        });
        clock_gall.setClickable(true);
        clock_gall.setOnClickListener(v->{
            Intent intent2=new Intent(this, clock.class);
            Animation animation_for_spaceship = AnimationUtils.loadAnimation(this, R.anim.spaceshipspecial);
            spaceship.startAnimation(animation_for_spaceship);
            Animation animation1=AnimationUtils.loadAnimation(this,R.anim.zoomin);
            ImageView splash=findViewById(R.id.splash);
            animation1.setDuration(500);
            new Handler().postDelayed(()->{
                splash.startAnimation(animation1);
            },1000);
            new Handler().postDelayed(() -> {
                spaceship.setImageResource(0);
                startActivity(intent2);
                finish();
            }, 1400);
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

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}