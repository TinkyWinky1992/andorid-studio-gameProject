package com.example.movementplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService extends Service implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener
        ,MediaPlayer.OnInfoListener,MediaPlayer.OnErrorListener{
    MediaPlayer mediaPlayer = new MediaPlayer();
    int linkone;
    public MyService() {
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnErrorListener(this);
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //get link from the intent
         linkone = intent.getIntExtra("link",R.raw.musictwo);

        if(mediaPlayer.isPlaying()==false) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), linkone);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100,100);
            mediaPlayer.start();
        }

        return START_STICKY_COMPATIBILITY;//play the music till service ended
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer!=null )
        {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop(); //stop the music
                mediaPlayer.release(); //release resources
            }

        }

        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }



    @Override
    public void onCompletion(MediaPlayer mp) {
        if(this.mediaPlayer.isPlaying()==true){
            this.mediaPlayer.stop();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    if(this.mediaPlayer.isPlaying()==false){
        this.mediaPlayer.start();
    }
    }



}