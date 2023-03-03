package com.example.movementplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class tutorial extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mainLayout;
    ImageView imageViewExplain;
    TextView textViewExplain;
    Button btnNext;
    private CharSequence charSequence;
    private ServiceScreen serviceScreen;
    private View decorView;
    ArrayList<Integer> arrayListImage;
    private int index;
    private long delay=100;
    private int counterOfImages;
    private Handler handler=new Handler();
  public void log(){
      decorView = getWindow().getDecorView();
      serviceScreen = new ServiceScreen(decorView);
      mainLayout=findViewById(R.id.backgroundLayout);
      imageViewExplain=findViewById(R.id.imageExplain);
      textViewExplain=findViewById(R.id.TextTutorialExplain);
      btnNext=findViewById(R.id.nextExample);
      btnNext.setOnClickListener(this);
      arrayListImage=new ArrayList<>();
      arrayListImage.add(R.drawable.onetutorial);
      arrayListImage.add(R.drawable.twotutorial);
      arrayListImage.add(R.drawable.threetutorail);
      arrayListImage.add(R.drawable.fourtutorial);
      arrayListImage.add(R.drawable.fivetutorail);
      arrayListImage.add(R.drawable.sixtoturial);
      counterOfImages=0;
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        log();


    }



    Runnable runanim = new Runnable() {
        @Override
        public void run() {
            textViewExplain.setText(charSequence.subSequence(0, index++));
            //when our animation stop
            if (index <= charSequence.length())
                handler.postDelayed(runanim,delay); }
        //animation Text activity
    };
    private void TextAnimation(CharSequence csStr) {
        //set another text
        charSequence = csStr;
        //clearing index
        index = 0;
        //clearing main textView
        textViewExplain.setText("");
        handler.removeCallbacks(runanim);
        //running the handler
        handler.postDelayed(runanim, delay);
    }
    public void SetTextExample(){
        if(counterOfImages==1){
            TextAnimation("With this joystick you can move your player in the map.");

        }
        if(counterOfImages==2){
            TextAnimation("You have two buttons one of them is gray and the other one is purple." +
                    "   with the gray one you punch,and with the purple one you jump.");

        }
        if(counterOfImages==3){
            TextAnimation("This is you , the player. ");

        }
        if(counterOfImages==4){
            TextAnimation(" The enemy is the one you need to kill, " +
                    "he tries to kill you,so you have to kill him before you will be killed.");

        }
        if(counterOfImages==5){
            TextAnimation("You can use the health bar" +
                    " to see your health and if the health bar will be null so you will die.");

        }
        if(counterOfImages==6){
            TextAnimation("This is the timer, with the timer you can see how much time you have survived. " +
                    "it will save the amount of time the player survived and it will show it in the state. ");

        }
    }
    @Override
    public void onClick(View view) {
        if(counterOfImages==6){

            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(btnNext.isPressed() && counterOfImages<=6){
            imageViewExplain.setImageResource(arrayListImage.get(counterOfImages));
            counterOfImages++;
            SetTextExample();
        }


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