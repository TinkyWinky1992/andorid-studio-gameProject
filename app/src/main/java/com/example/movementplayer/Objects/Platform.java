package com.example.movementplayer.Objects;

import android.graphics.Canvas;
import android.util.Log;

import com.example.movementplayer.ImageSnippingTool.PlatformView;
import com.example.movementplayer.GamePanel.GameDisplay;

public class Platform extends GameObject{
    private GameObject TargetObject;
    private PlatformView platformView;
    private GameDisplay gameDisplay;
    private int ScreenWidht;

    public Platform(double TemppositionX, double TempPositionY, GameObject targetObject, PlatformView platformView,
                    int sWidht,GameDisplay TempGameDisplay) {
        super(TemppositionX, TempPositionY,false);
        this.ScreenWidht=sWidht;
        this.TargetObject=targetObject;
        this.platformView=platformView;
        this.HeightImage=platformView.getPlatformsprite().getBitmap().getHeight();
        this.WightImage=platformView.getPlatformsprite().getBitmap().getWidth();
        this.gameDisplay=TempGameDisplay;
    }

    @Override
    public void update(GameObject gameObject2, GameObject gameObject) {
        /**platform logic while move to the upside way from the player
         */
            velocityX = (this.TargetObject.getPositionX() * -1);
            if(!gameObject.isonplatform)
                velocityY = this.TargetObject.getPositionY() * -1 + platformView.getPlatformsprite().getBitmap().getHeight();
        positionX = velocityX;
        positionX=positionX+(this.ScreenWidht)-gameObject.positionX;
        positionY = velocityY;
       // Log.i("positionX",String.valueOf(positionX));

    }

    @Override
   public void Draw(Canvas canvas) {
        platformView.draw(canvas,positionX,positionY);
    }

    @Override
    public int getHeightImage() {
        return super.getHeightImage();
    }

    @Override
    public int getWightImage() {
        return super.getWightImage();
    }
}

