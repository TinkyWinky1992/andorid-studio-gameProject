package com.example.movementplayer;
import android.os.Handler;
import android.view.View;

public class ServiceScreen {
    private View decorView;

    public ServiceScreen(View decorView){
        this.decorView = decorView;
    }




           // * helper method for onWindowFocusChanged method that hide the bars
    public void hideSystemBars(){
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                // Any time a user swipes from an edge, the system takes care of revealing the system bars
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                // Hide the system bars
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY// status bar
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);// navigation bar

    }

}

