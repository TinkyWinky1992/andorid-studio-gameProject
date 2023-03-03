
package com.example.movementplayer.GamePanel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.example.movementplayer.Objects.GameObject;
import com.example.movementplayer.R;
/**Create the Arena */
public class GameDisplay {
    public static Rect DISPLAYRECT ;
    private final int  WorldsizeX;
    private final int WorldsizeY;
    private Bitmap bitmapArena;
    private  int gameCenterX;
    private  int gameCenterY;
    private GameObject centerObject;

    public GameDisplay(Context context, int screenWight, int screenHeight, GameObject TempcenterObject) {

        this.WorldsizeX = screenWight;
        this.WorldsizeY = screenHeight;
        this.centerObject=TempcenterObject;
        this.DISPLAYRECT=new Rect(0,0,WorldsizeX,this.WorldsizeY);
        this.bitmapArena= BitmapFactory.decodeResource(context.getResources(), R.drawable.maptrail);

    }
    public void update(){
        gameCenterX=(int)centerObject.getPositionX();
        gameCenterY=(int)centerObject.getPositionY();

    }


    public void drawBackGround(Canvas canvas){

        canvas.drawBitmap(bitmapArena, null,DISPLAYRECT, null);
    }

    public int getWorldsizeX() {
        return WorldsizeX;
    }


    public int getWorldsizeY() {
        return this.WorldsizeY;
    }
}
