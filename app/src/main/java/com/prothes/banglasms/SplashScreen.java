package com.prothes.banglasms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    private LottieAnimationView animationView1,animationView2,animationView3;
    private int progress;
    private Typeface typeface;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.bar));
        View view = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        view.setSystemUiVisibility(uiOption);
        setContentView(R.layout.splash_screen);

        animationView1 = findViewById(R.id.animationView1);
        animationView2 = findViewById(R.id.animationView2);
        animationView3 = findViewById(R.id.animationView3);
        textView = findViewById(R.id.textView);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/english.ttf");
        textView.setTypeface(typeface);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
                goToHomePage();
            }
        });
        thread.start();

    }

    public void startProgress(){
        for (progress = 40; progress<=100; progress = progress + 20){
            try {
                Thread.sleep(2200,1000);
                animationView3.setProgress(progress);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void goToHomePage(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}