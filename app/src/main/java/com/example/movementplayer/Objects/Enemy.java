package com.example.movementplayer.Objects;

import android.graphics.Canvas;
import android.util.Log;

import com.example.movementplayer.Animation.AnimationHealthBar;
import com.example.movementplayer.Animation.EnemyAnimation;
import com.example.movementplayer.GamePanel.GameDisplay;
import com.example.movementplayer.GamePanel.GameLoop;
import com.example.movementplayer.Sprite.HealingBarSprite;
import com.example.movementplayer.StateObject.EnemyState;
import com.example.movementplayer.StateObject.HealthBarState;


/**
Enemy is a object on the game , his target to make the game more harder to the player by the difficult of the game
 */
public class Enemy extends GameObject {
    private int speed_per_second;
    private double Max_speed;
    private static final double GRAVITY = 1;
    private EnemyState enemyState;
    private EnemyAnimation enemyAnimation;
    private GameObject gameObjectTracker;
    private Platform platform;
    private boolean isneedNewPoint;
    private boolean inRangeRight;
    private boolean inRangeLeft;
    private int move_RandomBetweenleftOrRight;
    private int  move_betweenDirectionX;
    //HealthBar
    private final HealthBar healthBar;
    private AnimationHealthBar animationHealthBar;
    private HealthBarState healthBarState;
    //distance
    private double distanceBetWeenObjectToEnemy;
    private boolean isOnCollision;


    public Enemy( EnemyAnimation ObjectAnimation, GameObject gameObjectTemp,
                 HealingBarSprite healingBarSprite, Platform TempPlatform,int screenWidth) {
        super(Math.random()*gameObjectTemp.getPositionX(), Math.random()*gameObjectTemp.getPositionY(), false);
        this.enemyState=new EnemyState(this);

        this.enemyAnimation=ObjectAnimation;
        this.gameObjectTracker=gameObjectTemp;
        this.HeightImage= (int) enemyAnimation.getcharacterSize();
        this.isneedNewPoint=true;
        this.platform=TempPlatform;
        this.inRangeRight=true;
        this.inRangeLeft=true;
        /**HealthBar**/
        this.animationHealthBar=new AnimationHealthBar(healingBarSprite.getHealingBarView());
        this.healthBar=new HealthBar(positionX+getWightImage(),positionY+getHeightImage(),this,animationHealthBar);
        this.healthBarState=new HealthBarState(this);
        this.distanceBetWeenObjectToEnemy= getdistancebetweenThePlayer(this,this.gameObjectTracker);
        this.isOnCollision=false;
        this.speed_per_second=screenWidth/2;
        this.Max_speed=speed_per_second/gameObjectTemp.getHeightImage();

    }
    public  static  boolean readyToSpawn(HealthBar healthBar){
        if(healthBar.getHealthBarState().getState()== HealthBarState.State.NON_HP){

            return true;
        }
        else
            return false;
    }
    @Override
    public void update(GameObject Player, GameObject gameObject) {
        //enemy  have a area zone that can target player when they are in the zone
        //enemy moving randomly around the map
        //when enemy detected player the enemy will start to
        this.healthBar.update(this,this);
        this.enemyState.updateMoving();
        positionY+=velocityY;
        positionX+=velocityX;
        //calculate the distance between enemy to player
        this.distanceBetWeenObjectToEnemy=  getdistancebetweenThePlayer(this,this.gameObjectTracker);
        //distance between the collision zone form the
        double distanceCollision=gameObjectTracker.getHeightImage()*2;
        if(OnPlatForm(gameObject)) {
            if(velocityX!=0){
                lastplayerlocation=velocityX;
            }

            if (distanceCollision > this.distanceBetWeenObjectToEnemy) {

                //distance vector X
                double directionX = this.gameObjectTracker.getPositionX() - positionX;
                //calculate the way that the enemy need to go to get the player
                double vectorX = directionX / distanceBetWeenObjectToEnemy;
                if(distanceCollision-gameObjectTracker.getHeightImage()>this.distanceBetWeenObjectToEnemy){
                    this.isOnCollision = true;
                    vectorX=0;
                }
                velocityX = (vectorX * Max_speed);

            } else {
                this.isOnCollision=false;
                //if the enemy  his not in the range of the player,
                //the enemy will move in randomizer ways
                //create direction point to know which direction we want to go.
                //after we create our direction we calculate the distance between the player from the enemy
                //then we add the direction to the positions
                /**PLatform range between the enemy to the platform*/
                /**
                 */
                if(positionX<=platform.getPositionX()+HeightImage*2){
                    inRangeLeft=true;
                }
                else{
                    inRangeLeft=false;
                }
                if(positionX>=platform.getWightImage()-platform.getPositionX()+112){
                    inRangeRight=true;
                }
                else{
                    inRangeRight=false;
                }
                if(inRangeRight){
                    move_RandomBetweenleftOrRight=1;
                }
                else if(inRangeLeft){
                    move_RandomBetweenleftOrRight=2;
                }



                if(this.isneedNewPoint && !inRangeLeft && !inRangeRight){
                    move_RandomBetweenleftOrRight=(int)(Math.random()*2-1+1)+1;
                }
                if(isneedNewPoint){
                    if (move_RandomBetweenleftOrRight == 1 ){
                        move_betweenDirectionX = (int) (((int) (Math.random() * ((gameObject.getPositionX() / 2) - positionX + 1) + positionX)));
                    }
                   if(move_RandomBetweenleftOrRight == 2){
                       move_betweenDirectionX= (int) ((int) ((Math.random() *((gameObject.getWightImage()/2)-positionX)) +positionX));
                   }
                }


                    this.isneedNewPoint = false;
                    //calculate the distance between enemy to direction
                    double distanceX = Math.sqrt(Math.pow(move_betweenDirectionX - positionX, 2));
                    double distance_DirectionX =( move_betweenDirectionX - positionX);
                    //calculate vector to the direction
                    double vectorX = distance_DirectionX / distanceX;
                    velocityX = vectorX * Max_speed;
                    //left
                    if (positionX <= move_betweenDirectionX && move_RandomBetweenleftOrRight==1) {
                        isneedNewPoint = true;
                    }
                    //right
                    if (positionX >= move_betweenDirectionX&& move_RandomBetweenleftOrRight==2) {
                        isneedNewPoint = true;
                    }

                }


        }

        else {
            velocityY+= GRAVITY;
        }


    }
    private  boolean OnPlatForm(GameObject gameObject) {
        //calculate distance between Objects
        //    double distancebetweenObjectToScreen=Math.sqrt(Math.pow(positionX-,)
        int sizePlatform = (int) (gameObject.getPositionX() - gameObject.getWightImage() * -1);
        if (this.positionY >= gameObject.getPositionY() - this.enemyAnimation.getcharacterSize() && this.positionX <= sizePlatform && this.positionX >= gameObject.getPositionX()) {
            this.positionY = gameObject.getPositionY() - this.enemyAnimation.getcharacterSize();
            //positionX<=gameObject.getPositionX()*-1
            this.isonplatform = true;
        }
        return this.isonplatform;
    }


    @Override
    public void Draw(Canvas canvas) {
    this.healthBar.Draw(canvas);
    this.enemyAnimation.draw(canvas,(int)positionX,(int)positionY,this);

    }

    @Override
    public double getlastlocation(){
        return lastplayerlocation;
    }

    public EnemyState getEnemyState() {
        return this.enemyState;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public boolean isOnCollision() {
        return isOnCollision;
    }

    public double getDistanceBetWeenObjectToEnemy() {
        return distanceBetWeenObjectToEnemy;
    }
}
