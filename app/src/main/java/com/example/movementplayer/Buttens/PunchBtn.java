package com.example.movementplayer.Buttens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.movementplayer.R;
/**Punch Button, make a the player Punch if the user want to press it*/
public class PunchBtn {
    private final double positionX;
    private final double positionY;
    private double Radius;
    private Paint paint;
    private boolean IsPunch;

    public PunchBtn(Context context, double pX, double pY, double radius) {
        this.positionX = pX;
        this.positionY = pY;
        this.Radius = radius;
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.purple_700);
        paint.setColor(color);
        this.IsPunch = false;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) Radius, paint);
    }
    //detected if the touch of the player was on the circle
    public void isPressedPunch(double touchPositionX, double touchPositionY) {
        if(Math.pow((touchPositionX-positionX),2)+Math.pow((touchPositionY-positionY),2)<Math.pow(Radius,2)){
            setisPunch(true);

        }
        else
            setisPunch(false);


    }
    public void setisPunch(boolean isPunchTemp){  IsPunch=isPunchTemp; }

    public boolean isPunch(){
        return IsPunch;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getRadius() {
        return Radius;
    }

}





