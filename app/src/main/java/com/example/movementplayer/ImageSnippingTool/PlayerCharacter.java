package com.example.movementplayer.ImageSnippingTool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.movementplayer.Sprite.PlayerCharacterSprite;
/**Create Player Object using  the PlayerState image*/
//Snipping the tail image
public class PlayerCharacter  {

    private final PlayerCharacterSprite playerMain;
    private  final Rect rects;


    public PlayerCharacter(PlayerCharacterSprite playerPicture, Rect rect) {

        this.playerMain=playerPicture;
        this.rects=rect;


    }
    public void draw(Canvas canvas, int positionX, int positionY){


        canvas.drawBitmap(playerMain.getBitmap(),rects,
                new Rect(positionX,positionY,positionX+rects.width(),positionY+rects.height()),null);
    }
    public int getHeight(){
        return rects.height();
    }

    public Rect getRects() {
        return rects;
    }


}
