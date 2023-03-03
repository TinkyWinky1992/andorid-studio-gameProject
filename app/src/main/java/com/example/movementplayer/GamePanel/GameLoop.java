package com.example.movementplayer.GamePanel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Message;
import android.view.SurfaceHolder;

import androidx.core.content.ContextCompat;

import com.example.movementplayer.R;

public class GameLoop extends  Thread {
    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = (1E+3 / MAX_UPS);
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private int averageFPS;
    private int averageUPS;
    private int TimeInSecond;
    private int TimeInMinute;


    public GameLoop(GameView gameView, SurfaceHolder surfaceHolder) {
        this.gameView = gameView;
        this.surfaceHolder = surfaceHolder;
    }

    public int getAverageFPS() {
        return averageFPS;
    }

    public int getTimeInMinute() {
        return TimeInMinute;
    }

    public int getTimeInSecond() {
        return TimeInSecond;
    }

    public void startLoop()
    {
        isRunning = true;
        start();
        TimeInSecond=0;
        TimeInMinute=0;
    }
    public void UpdateIsGameOver()
    {
        Message GameOvermassage = this.gameView.getGameOverDialog().obtainMessage();
        GameOvermassage.getData().putBoolean("Over",true);
        this.gameView.getGameOverDialog().sendMessage(GameOvermassage);
    }
    @Override
    public void run()
    {
        super.run();
        //counting frames
        int updatecount = 0;
        int frames = 0;
        int TimeinMeleesecond=0;
        //the time in milisecens
        long startTime;
        long elapsedTime;
        long sleepTime;

        //game loop
        Canvas canvas=null;
        startTime = System.currentTimeMillis();
        while (isRunning) {
            //calculate the game time
            TimeinMeleesecond++;
            if(TimeinMeleesecond>60){
                TimeinMeleesecond=0;
                this.TimeInSecond++;
            }
            if(this.TimeInSecond>60){
                this.TimeInSecond=0;
                this.TimeInMinute++;
            }
            //update and render game
            try {
                canvas=surfaceHolder.lockCanvas();
                synchronized(surfaceHolder){
                    gameView.update();
                    updatecount++;
                    if(!gameView.isIsgameOver()){
                        gameView.draw(canvas);
                    }

                    if(gameView.isIsgameOver()){
                        UpdateIsGameOver();
                    }



                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas!=null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frames++;

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            //Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updatecount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Skip frames to keep up with target ups
                while (sleepTime < 0 && updatecount < MAX_UPS - 1) {
                    gameView.update();
                    updatecount++;
                    elapsedTime = System.currentTimeMillis() - startTime;
                    sleepTime = (long) (updatecount * UPS_PERIOD - elapsedTime);
                }
            }
            //frames per second mathod
            frames++;
            updatecount++;
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageFPS = (int) (frames / (1E-3 * elapsedTime));
                averageUPS = (int) (updatecount / (1E-3 * elapsedTime));
                frames = 0;
                updatecount = 0;
                startTime = System.currentTimeMillis();


            }
        }

    }
}

