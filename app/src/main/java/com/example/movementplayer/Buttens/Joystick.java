package com.example.movementplayer.Buttens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**Navigate the player using the joystick*/
public class Joystick {

    private final float outerCircleCenterPositionX;
    private final float outerCircleCenterPositionY;

    private  float innerCircleCeneterPostionX;
    private float innerCircleCeneterPostionY;
    private int dpH;
    private  int dpW;

    private  final float outerCircleRadius;
    private final float innerCircleRadius;

    private Paint outerCirclePaint;
    private Paint innerCirclePaint;

    private double joystickCenterToTouchDistance;
    private boolean isPressed;

    private double actuatorX;
    private double actuatorY;



    public Joystick(float centerPostionX, float centerPostionY, float outerCircleRadius, int innerCircleRadius, int hieght, int Wigth ){
            outerCircleCenterPositionX= centerPostionX;
            outerCircleCenterPositionY= centerPostionY;
            innerCircleCeneterPostionX= centerPostionX;
            innerCircleCeneterPostionY= centerPostionY;
        //Radios of controller circle
            this.outerCircleRadius=outerCircleRadius;
            this.innerCircleRadius=innerCircleRadius;
            //paint of outer circle controler
            this.outerCirclePaint=new Paint();
            outerCirclePaint.setColor(Color.GRAY);
            outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            //paint of inner circle controler
             this.innerCirclePaint=new Paint();
             innerCirclePaint.setColor(Color.BLUE);
             innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
             this.dpH=hieght;
             this.dpW=Wigth;


    }

    public  void setIsPressed(boolean isPressed) {
        this.isPressed=isPressed;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(outerCircleCenterPositionX,outerCircleCenterPositionY,outerCircleRadius,outerCirclePaint);
        canvas.drawCircle(innerCircleCeneterPostionX,innerCircleCeneterPostionY,innerCircleRadius,innerCirclePaint);




    }
    public void update(){
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCeneterPostionX=(int)(outerCircleCenterPositionX+actuatorX*outerCircleRadius);
        innerCircleCeneterPostionY=(int)(outerCircleCenterPositionY+actuatorY*outerCircleRadius);
    }



//detected if the touch that the user click on,is on the outer circle
    public boolean isPressed(double touchPositionX, double touchPositionY) {

            joystickCenterToTouchDistance=Math.sqrt(
                    Math.pow(outerCircleCenterPositionX - touchPositionX, 2) +
                    Math.pow(outerCircleCenterPositionY - touchPositionY, 2)
            );
        return  joystickCenterToTouchDistance <outerCircleRadius;
    }


    public boolean getIsPressed() {
        return isPressed;
    }
    //moving the inner circle
    public void setActuator(double touchPositisonX, double touchPositionY, double XScreen) {
        double deltaX=touchPositisonX-outerCircleCenterPositionX;
        double deltaY=touchPositionY-outerCircleCenterPositionY;
        double deltaDistance=Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        if(deltaDistance<outerCircleRadius ){
            if(deltaDistance< XScreen){
                actuatorX=deltaX/outerCircleRadius;
                actuatorY=deltaY/outerCircleRadius;
            }

        }else{
            if(deltaDistance< XScreen){
                actuatorX= deltaX/deltaDistance;
                actuatorY= deltaY/deltaDistance;
            }

        }

    }

    public void resetActuator() {
        actuatorX=0.0;
        actuatorY=0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
