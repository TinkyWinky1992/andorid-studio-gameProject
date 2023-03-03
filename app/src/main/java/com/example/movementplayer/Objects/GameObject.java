package com.example.movementplayer.Objects;

import android.graphics.Canvas;

import com.example.movementplayer.GamePanel.GameDisplay;

/**Game Object is manage the positions and the velocity of the Object in game like enemy and the player */
public abstract class GameObject {
    protected double positionX;

    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected boolean isonplatform;
    protected int WightImage;
    protected int HeightImage;
    protected double lastplayerlocation;
    public GameObject(double TemppositionX,double TempPositionY,boolean isonplatform) {
        this.positionX=TemppositionX;
        this.positionY=TempPositionY;
        this.isonplatform=isonplatform;

    }

    protected static double getdistancebetweenThePlayer(GameObject ObjectTempOne, GameObject ObjectTempTwo) {
        return Math.sqrt(Math.pow(ObjectTempOne.positionY- ObjectTempTwo.positionY, 2) +
                Math.pow(ObjectTempOne.positionX - ObjectTempTwo.positionX, 2));
    }
    abstract void update(GameObject GameObjectTracker,GameObject gameObject);
    abstract void Draw(Canvas canvas);

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public boolean isIsonplatform() { return isonplatform; }

    public int getHeightImage() {
        return HeightImage;
    }

    public int getWightImage() {
        return WightImage;
    }

    public double getlastlocation() {
       return lastplayerlocation;
    }


}
