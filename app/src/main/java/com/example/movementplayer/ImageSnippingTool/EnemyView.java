package com.example.movementplayer.ImageSnippingTool;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.movementplayer.Sprite.EnemySprite;
import com.example.movementplayer.Sprite.PlayerCharacterSprite;

public class EnemyView {
    private final EnemySprite EnemyImage;
    private  final Rect rects;


    public EnemyView(EnemySprite EnemyPicture, Rect rect) {

        this.EnemyImage=EnemyPicture;
        this.rects=rect;


    }
    public void draw(Canvas canvas, int positionX, int positionY){


        canvas.drawBitmap(this.EnemyImage.getBitmap(),rects,
                new Rect(positionX,positionY,positionX+rects.width(),positionY+rects.height()),null);
    }
    public int getHeight(){
        return rects.height();
    }

    public Rect getRects() {
        return rects;
    }
}
