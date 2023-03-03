package com.example.movementplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class settingsgame extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout mainLayout;
    Button btnplaygame;
    Button btnTutorial;
    Button Gobackbtn;
    private ServiceScreen serviceScreen;
    private View decorView;
    private void initview(){

        decorView = getWindow().getDecorView();
        serviceScreen = new ServiceScreen(decorView);
        mainLayout=findViewById(R.id.backgroundSettings);
        btnplaygame=findViewById(R.id.btnStartplaygame);
        btnTutorial=findViewById(R.id.btnStartTutorial);
        Gobackbtn=findViewById(R.id.btnGobackTomenu);
        Gobackbtn.setOnClickListener(this);
        btnplaygame.setOnClickListener(this);
        btnTutorial.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsgame);
        initview();
        //animation background
        animation_background(mainLayout);
    }



    //----------------Animation----------------//
    public void animation_background(ConstraintLayout constraintLayout){
        //creating background animation with background_list//
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start(); }
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

    @Override
    public void onClick(View view) {
        Intent intent;
        if(btnplaygame.isPressed()){
            intent=new Intent(this,MainActivity.class);

            startActivity(intent);
            finish();
        }
        else if(btnTutorial.isPressed()){
            intent=new Intent(this,tutorial.class);
            startActivity(intent);
            finish();
        }
        if(Gobackbtn.isPressed()){
            intent=new Intent(this,Menu.class);
            startActivity(intent);
            finish();
        }


    }

}