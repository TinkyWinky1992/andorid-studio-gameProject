package com.example.movementplayer.Objects;

import android.graphics.Canvas;

import com.example.movementplayer.Animation.AnimationHealthBar;
import com.example.movementplayer.GamePanel.GameDisplay;
import com.example.movementplayer.StateObject.HealthBarState;

/*
HealthBar display to the player , controlling the player health
 */
public class HealthBar extends  GameObject {
        private HealthBarState state;
        private AnimationHealthBar animationHealthBar;
        private boolean ActiviteChangeState;
    public HealthBar(double TemppositionX, double TempPositionY,GameObject gameObject,AnimationHealthBar TempAnimation) {
        super(TemppositionX, TempPositionY,false);
        this.state=new HealthBarState(gameObject);
        this.animationHealthBar=TempAnimation;
        this.WightImage=animationHealthBar.getWidth();
        this.ActiviteChangeState=false;
    }



    public HealthBarState getHealthBarState() {
        return  this.state;
    }

    public void setActiviteChangeState(boolean activiteChangeState) {
        ActiviteChangeState = activiteChangeState;
    }
    public boolean isActiviteChangeState() {
        return this.ActiviteChangeState;
    }

    @Override
    public void update(GameObject TempGameObject, GameObject gameObject) {
        /**
         * The Positions of the health bar will be the same positions of the player but ,
         * the position y have to be : Position player+ Player Height/2
         */
        positionX=gameObject.getPositionX()-(this.WightImage/2);
        positionY=gameObject.getPositionY()-gameObject.getHeightImage()/2;

    }

    @Override
    public void Draw(Canvas canvas) {
        animationHealthBar.draw(canvas,this.positionX,this.positionY,this);
    }

}
