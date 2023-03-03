package com.example.movementplayer.Objects;

import android.graphics.Canvas;
import android.util.Log;


import com.example.movementplayer.Animation.AnimationHealthBar;
import com.example.movementplayer.Buttens.Joystick;
import com.example.movementplayer.Buttens.Jumpbtn;
import com.example.movementplayer.Buttens.PunchBtn;
import com.example.movementplayer.Animation.AnimatorPlayer;
import com.example.movementplayer.GamePanel.GameLoop;
import com.example.movementplayer.Sprite.HealingBarSprite;
import com.example.movementplayer.StateObject.HealthBarState;
import com.example.movementplayer.StateObject.PlayerState;
import com.example.movementplayer.GamePanel.GameDisplay;

/**Player Object*/
public class Player extends GameObject {
    private int speed_per_second;
    private double Max_speed;
    private static final double GRAVITY = 1;
    private Joystick joystick;
    private PunchBtn punchBtn;
    private Jumpbtn jumpbtn;
    private AnimatorPlayer PlayerAnimate;
    private PlayerState playerState;
    private  double jumpvelocity;

    //HealthBar
    private final HealthBar healthBar;
    private AnimationHealthBar animationHealthBar;
    private HealthBarState healthBarState;
    public Player(double PositionX, double PositionY, Joystick TempJoyStick, PunchBtn TempPunchBtn,
         Jumpbtn TempJumpBtn, AnimatorPlayer animatorPlayer,HealingBarSprite healingBarSprite,int screenWidth) {
        super(PositionX, PositionY,false);
        this.joystick = TempJoyStick;
        this.punchBtn = TempPunchBtn;
        this.jumpbtn = TempJumpBtn;
        //the charachter
        this.PlayerAnimate = animatorPlayer;
        this.playerState = new PlayerState(this);
        this.HeightImage= (int) animatorPlayer.getcharacterSize();
        /**HealthBar**/

        this.animationHealthBar=new AnimationHealthBar(healingBarSprite.getHealingBarView());
        this.healthBar=new HealthBar(PositionX,PositionY+HeightImage,this,this.animationHealthBar);
        this.healthBarState=new HealthBarState(this);
        this.speed_per_second=screenWidth/2;
        this.Max_speed=speed_per_second/60.0;
    }
    @Override
    public void Draw(Canvas canvas) {
        this.healthBar.Draw(canvas);
        PlayerAnimate.draw(canvas, (int) positionX, (int) positionY, this);
    }
    /**
     * Manage player movement
     */
    @Override
    public void update(GameObject Enemy, GameObject gameObject) {
        //add the velocity when the player move on axis x
        this.positionX += velocityX;
        //add the velocity when the player move on axis y
        this.positionY += jumpvelocity;
        /**Checking Y Positions*/
        //player on the ground
        if(OnPlatForm(gameObject) && playerState.getState()!= PlayerState.State.IS_DEATH_LEFT && playerState.getState()!=PlayerState.State.IS_DEATH_RIGHT){
            this.velocityX = this.joystick.getActuatorX() * Max_speed;
            this.velocityY = this.joystick. getActuatorY() * Max_speed;

            this.jumpvelocity=0;
            //save the value of the velocity when the player stop move
            if (velocityX != 0) {
                this.lastplayerlocation = this.velocityX;
            }
            //when the player press on the jump button, add on axis Y velocity
            if (jumpbtn.isJump()) {
                this.jumpvelocity-=this.PlayerAnimate.getcharacterSize()/10;
            }

        }
        //if the player not on the ground, pull him down to the ground and disable jump button

         else{
            this.punchBtn.setisPunch(false);
            this.jumpbtn.setisJump(false);
            this.jumpvelocity+=GRAVITY;
        }
        /**Checking X Positions*/
        //Sets a left boundary ( of the screen)
        if (positionX <= 0)
            this.positionX = 0;



        //detect if player click on the punch button

        this.playerState.updateMoving(punchBtn,healthBar,PlayerAnimate);
        this.healthBar.update(gameObject,this);
    }
    private  boolean OnPlatForm(GameObject gameObject){
         //calculate distance between Objects
            int sizePlatform= (int) (gameObject.getPositionX()-gameObject.getWightImage()*-1);
        if(this.positionY>=gameObject.getPositionY()-this.PlayerAnimate.getcharacterSize() && this.positionX<=sizePlatform && this.positionX>=gameObject.getPositionX()){
            this.positionY=gameObject.getPositionY()-this.PlayerAnimate.getcharacterSize();
             this.isonplatform=true;
        }

        else
            this.isonplatform= false;
        return  this.isonplatform;
    }


    @Override
    public double getlastlocation(){
        return lastplayerlocation;
    }
    public PlayerState getPlayerState() {
        return playerState;
    }

    public PunchBtn getPunchBtn() {
        return punchBtn;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
}

