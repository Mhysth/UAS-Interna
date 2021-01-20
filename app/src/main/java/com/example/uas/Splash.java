package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.uas.utils.SharedPreferenceHelper;

public class Splash extends AppCompatActivity {

    private ImageView logo;
    SharedPreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.imageView);
        helper = SharedPreferenceHelper.getInstance(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (helper.getAccessToken().isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent onBoard = new Intent(Splash.this, Login.class);
                    onBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(onBoard);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, 2500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent onBoard = new Intent(Splash.this, MainActivity.class);
                    onBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(onBoard);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, 2500);
        }
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(a);
    }
}