package com.example.movementplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class load extends AppCompatActivity implements View.OnClickListener {
  private TextView EnjoyText;
  private  Button buttonStart;
    //Text animation mathod verbles
    private CharSequence charSequence;
    private ServiceScreen serviceScreen;
    private View decorView;
    private int index;
    private long delay=200;
    private Handler handler=new Handler();

    @SuppressLint("WrongViewCast")
    public void initview(){
        decorView = getWindow().getDecorView();
        serviceScreen=new ServiceScreen(decorView);
        buttonStart=findViewById(R.id.startGame);
        EnjoyText=findViewById(R.id.EnjoyGameText);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        initview();
        buttonStart.setOnClickListener(this);
        animation_background();

        //Text Animation-----------------------------
        TextAnimation("Enjoy Playing :D");
    }

    //    method of no arguments for text animation
    Runnable runanim = new Runnable() {
        @Override
        public void run() {
            EnjoyText.setText(charSequence.subSequence(0, index++));
            //when our animation stop
            if (index <= charSequence.length())
                handler.postDelayed(runanim,delay); }
    };
    //animation Text activity
    public void TextAnimation(CharSequence csStr){
        //set another text
        charSequence =csStr;
        //clearing index
        index=0;
        //clearing main textView
        EnjoyText.setText("");
        handler.removeCallbacks(runanim);
        //running the handler
        handler.postDelayed(runanim,delay);
    }



    //animation Background activity
    public void animation_background(){
        //creating background animation with background_list//
        LinearLayout linearLayout = findViewById(R.id.Splash);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start(); }
    //Button mathod
    public void ToGameScreen(View view) {
        openMainActivity(); }
    private void openMainActivity() {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent= new Intent(this,Menu.class);
        startActivity(intent);

    }
    //-------------------------FULLSCREEN MATHOD---------------------------//
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            this.serviceScreen.hideSystemBars();
    }
    @Override
    protected void onResume() {

        super.onResume();

        this.serviceScreen.hideSystemBars();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        System.exit(1);
    }

}
