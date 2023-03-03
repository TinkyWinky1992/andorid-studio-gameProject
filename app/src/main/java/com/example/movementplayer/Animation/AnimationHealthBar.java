package com.example.movementplayer.Animation;

import android.graphics.Canvas;

import com.example.movementplayer.ImageSnippingTool.HealingBarView;
import com.example.movementplayer.Objects.HealthBar;

public class AnimationHealthBar {
    private HealingBarView[] healingBarView;

    public AnimationHealthBar (HealingBarView[] HealthBar) {
        this.healingBarView=HealthBar;
    }

    private void drawFrame(Canvas canvas, int positionX, int positionY, HealingBarView healingBarView) {
        healingBarView.draw(canvas,(int)positionX, (int)positionY);

    }
    public void draw(Canvas canvas, double positionX, double positionY, HealthBar healthBar) {
        switch (healthBar.getHealthBarState().getState() ){
            case FULL_HP:
                drawFrame(canvas,(int)positionX,(int)positionY,healingBarView[0]);
                break;
            case ABOUT_FULL_HP:
                drawFrame(canvas,(int)positionX,(int)positionY,healingBarView[1]);
                break;
            case HALF_HP:
                drawFrame(canvas,(int)positionX,(int)positionY,healingBarView[2]);
                break;
            case LOW_HP:
                drawFrame(canvas,(int)positionX,(int)positionY,healingBarView[3]);
                break;
            case NON_HP:
                drawFrame(canvas,(int)positionX,(int)positionY,healingBarView[4]);
        }

    }
    public int getWidth(){
        return healingBarView[0].getWidth();
    }

}
