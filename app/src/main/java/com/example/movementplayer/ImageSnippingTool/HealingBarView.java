package com.example.movementplayer.ImageSnippingTool;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.movementplayer.Sprite.HealingBarSprite;
import com.example.movementplayer.Sprite.PlayerCharacterSprite;

public class HealingBarView {
    private final HealingBarSprite healingBarSprite;
    private  final Rect rects;


    public HealingBarView(HealingBarSprite HealingBarPicture, Rect rect) {

        this.healingBarSprite=HealingBarPicture;
        this.rects=rect;


    }
    public void draw(Canvas canvas, int positionX, int positionY){


        canvas.drawBitmap(healingBarSprite.getBitmap(),rects,
                new Rect(positionX,positionY,positionX+rects.width(),positionY+rects.height()),null);
    }
    public int getHeight(){
        return rects.height();
    }
    public int getWidth(){
        return rects.width();
    }

    public Rect getRects() {
        return rects;
    }


}
