package com.example.movementplayer.Buttens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.movementplayer.R;
/**Jump Button ,make the player jump if the user want to use it*/
public class Jumpbtn {
    private final double positionX;
    private final double positionY;
    private double Radius;
    private Paint paint;
    private boolean Isjump;

    public Jumpbtn(Context context, double pX, double pY, double radius){
        this.positionX=pX;
        this.positionY=pY;
        this.Radius=radius;
        paint=new Paint();
        int color= ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
        this.Isjump=false;
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float)positionY,(float)Radius,paint);
    }
    //detected if the touch of the user was in the circle
    public void isPressedJump(double touchPositionX, double touchPositionY) {
        if(Math.pow((touchPositionX-positionX),2)+Math.pow((touchPositionY-positionY),2)<Math.pow(Radius,2)){
            setisJump(true);

        }
        else
            setisJump(false);

    }
    public void setisJump(boolean isjump){
        Isjump=isjump;

    }
    public boolean isJump(){
        return Isjump;
    }

    public Paint getPaint() {
        return paint;
    }
}
