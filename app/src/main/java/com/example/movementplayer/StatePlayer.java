package com.example.movementplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatePlayer extends AppCompatActivity implements View.OnClickListener{
    TextView scoreView;
    Button GobackBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public void initview() {
        scoreView=findViewById(R.id.scoreSurvived);
        GobackBtn=findViewById(R.id.btnGoBack);
        GobackBtn.setOnClickListener(this);
        sharedPreferences=this.getSharedPreferences(String.valueOf(R.string.shared_pref_file_name),MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String score=sharedPreferences.getString(String.valueOf(R.string.share_max_score_player),"");
        scoreView.setText(score);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_player);
        initview();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,Menu.class);
        startActivity(intent);
        finish();
    }
}