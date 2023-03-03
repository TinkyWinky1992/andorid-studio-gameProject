package com.example.movementplayer;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.movementplayer.Buttens.Jumpbtn;
import com.example.movementplayer.Buttens.PunchBtn;
import com.example.movementplayer.GamePanel.GameView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int READ_PHONE_STATE_PERMISSION = 1 ;
    private View decorView;
    private ServiceScreen serviceScreen;
    private FrameLayout Background;
    private GameView game;
    private Button backmenubtn;
    private TextView SurvivedScoreText;
    private Dialog dialog;
    private Dialog PauseDialog;
    private Button GobacktoGameBTN;
    private String TempTextSurvivedScore;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    public static final String EXTRA_LEVEl="score";
    private MyReceiver myReceiver;

    public  void initview(){
        myReceiver=new MyReceiver();
        decorView = getWindow().getDecorView();
        serviceScreen=new ServiceScreen(decorView);
        Background=findViewById(R.id.lobby);
        dialog= new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.game_over_dialog);
        backmenubtn=dialog.findViewById(R.id.ButtonGoBack);
        SurvivedScoreText=dialog.findViewById(R.id.survivedText);
        PauseDialog=new Dialog(this,R.style.DialogStyle);
        PauseDialog.setContentView(R.layout.pause_dialog);
        GobacktoGameBTN=PauseDialog.findViewById(R.id.ButtonGoBackToplay);
        sharedPref=this.getSharedPreferences(String.valueOf(R.string.shared_pref_file_name),MODE_PRIVATE);
        editor=sharedPref.edit();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //to activate the theme
        setTheme(R.style.gameTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        phonePermission();
        //incoming calls
        this.myReceiver = new MyReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle=intent.getExtras();
                //String receivedText = ;

                if (bundle.getString("pause_game") !=null && bundle.getString("pause_game").equals("pause")) {
                    ShowpauseDialog();
                }

            }
        };
registerReceiver(this.myReceiver,new IntentFilter("broadcast"));
    }
    //Game over Dialog
    public Handler GameOverHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message message) {
            TempTextSurvivedScore="Survived :" +" " +game.getTimer();
            String timer=game.getTimer();

            ShowEndDialog();
        }

    };
    private void ShowEndDialog() {
        editor.putString(String.valueOf(R.string.share_max_score_player), TempTextSurvivedScore);
        editor.apply();


        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_4);
        SurvivedScoreText.setText(TempTextSurvivedScore);
        backmenubtn.setOnClickListener(this);
        //saving the score of the player in share preferences

        dialog.show();
    }
    private void ShowpauseDialog() {


        PauseDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_4);
        GobacktoGameBTN.setOnClickListener(this);
        //saving the score of the player in share preferences

        PauseDialog.show();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int dpHeight = this.Background.getHeight();
        int dpWidth = this.Background.getWidth();
        game = new GameView(this,dpHeight,dpWidth,GameOverHandler);

        if(hasFocus)
        {
            this.serviceScreen.hideSystemBars();
            //2 create the view

            Background.addView(game);


        }

    }


    @Override
    protected void onResume() {
        this.serviceScreen.hideSystemBars();

        super.onResume();




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
        if(backmenubtn.isPressed()){
            intent=new Intent(this,Menu.class);
            intent.putExtra(EXTRA_LEVEl,String.valueOf(TempTextSurvivedScore));
            startActivity(intent);
            finish();
        }
        else if(GobacktoGameBTN.isPressed()){
            PauseDialog.dismiss();
        }

        }
    private void phonePermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "permission already granted", Toast.LENGTH_SHORT).show();
        }
        else{
            //result will be deliverd to OnrequestPermissionsResult
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            //this means fine location
            case READ_PHONE_STATE_PERMISSION:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    break;
                }
        }

    }
    }
