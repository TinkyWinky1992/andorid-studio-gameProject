package com.example.movementplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Menu extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {


    LinearLayout MenuLayout;
    private ServiceScreen serviceScreen;
    private View decorView;
    private Button btnStartGame;
    private Button btnStateGame;
    private Button btnOptionGame;
    private Intent serviceIntent;
    private ImageButton menubtn;
    private  Button btntomenu;
    private Switch musicSwitch;
    private boolean ismusicChecked;
    private Dialog dialogmenu;

    public void initview() {
        decorView = getWindow().getDecorView();
        serviceScreen = new ServiceScreen(decorView);
        dialogmenu= new Dialog(this, R.style.DialogStyle);
        dialogmenu.setContentView(R.layout.musicdialog);
        menubtn=findViewById(R.id.MenuButton);
        menubtn.setOnClickListener(this);
        MenuLayout = findViewById(R.id.MenuScreen);
        btntomenu=dialogmenu.findViewById(R.id.ButtonGoBackfromDialog);
        btntomenu.setOnClickListener(this);
        btnStartGame=findViewById(R.id.StartPlay);
        btnStartGame.setOnClickListener(this);
        btnStateGame=findViewById(R.id.State);
        btnStateGame.setOnClickListener(this);
        ismusicChecked=true;
       musicSwitch=dialogmenu.findViewById(R.id.musicswitch);
       musicSwitch.setOnClickListener(this);

        serviceIntent = new Intent(this,MyService.class);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initview();
        animation_background(MenuLayout);



    }

    //----------------Animation----------------//
    public void animation_background(LinearLayout linearLayout){
        //creating background animation with background_list//
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
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
        this.PlayAudio();
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
        if(btnStartGame.isPressed()){
            intent=new Intent(this,settingsgame.class);

            startActivity(intent);
            finish();
        }
        if(btnStateGame.isPressed()){
            intent=new Intent(this,StatePlayer.class);
            startActivity(intent);
            finish();
        }
        if(menubtn.isPressed()){
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menusettings);
            this.showMenuIcons(popupMenu);
            popupMenu.show();
        }

        if(musicSwitch.isPressed()){
            ismusicChecked=!ismusicChecked;
        }


        if(btntomenu.isPressed()){
            dialogmenu.dismiss();
        }


     

    }
    //must show icons
    private void showMenuIcons(PopupMenu popupMenu) {
        android.view.Menu menu =  popupMenu.getMenu();
        Method menuMethod;
        try {
            menuMethod = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
            menuMethod.setAccessible(true);
            menuMethod.invoke(menu, true);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void PlayAudio() {
        if(ismusicChecked){
            this.serviceIntent.putExtra("link",R.raw.musictwo);
            try
            {
                this.startService(this.serviceIntent);
            }
            catch (Exception e)
            {
                Toast.makeText(this,"cannot start service",Toast.LENGTH_LONG).show();
            }
        }
    }


    /**
     * Stop the service music
     */
    private void StopAudio() {
        try {
            this.stopService(serviceIntent);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"cannot stop service",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.musicbtnmenu:
                dialogmenu.show();
                dialogmenu.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(ismusicChecked){
                            PlayAudio();
                        }
                        else{
                            StopAudio();
                        }
                    }
                });

                return true;
            case R.id.exitbtnmenu:
                 onBackPressed();
                return true;

        }
        return false;
    }

}