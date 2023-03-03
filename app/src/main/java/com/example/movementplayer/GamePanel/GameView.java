package com.example.movementplayer.GamePanel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.example.movementplayer.Animation.AnimationHealthBar;
import com.example.movementplayer.Animation.EnemyAnimation;
import com.example.movementplayer.ImageSnippingTool.EnemyView;
import com.example.movementplayer.ImageSnippingTool.PlatformView;
import com.example.movementplayer.Buttens.Joystick;
import com.example.movementplayer.Buttens.Jumpbtn;
import com.example.movementplayer.Buttens.PunchBtn;
import com.example.movementplayer.Animation.AnimatorPlayer;
import com.example.movementplayer.Objects.Enemy;
import com.example.movementplayer.Objects.HealthBar;
import com.example.movementplayer.Objects.Platform;
import com.example.movementplayer.Objects.Player;
import com.example.movementplayer.Sprite.EnemySprite;
import com.example.movementplayer.Sprite.HealingBarSprite;
import com.example.movementplayer.Sprite.PlatformSprite;
import com.example.movementplayer.Sprite.PlayerCharacterSprite;
import com.example.movementplayer.StateObject.EnemyState;
import com.example.movementplayer.StateObject.HealthBarState;
import com.example.movementplayer.StateObject.PlayerState;
import com.example.movementplayer.R;

import java.util.ArrayList;
import java.util.List;

/**Main Game*/
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private  HealingBarSprite healingBarSprite;
    private  int screenWidht;
    private int screenHeight;
    private GameLoop gameLoop;
    private Context context;
    //Buttons
    private final Joystick joystick;
    private final Jumpbtn jumpbtn;
    private final PunchBtn punchBtn;
    //player
    private final Player player;
    private AnimatorPlayer Animator;
    private PlayerState playerState;//Enemy
    private List<Enemy> enemies=new ArrayList<>();
    private EnemyAnimation enemyAnimation;
    private EnemyState enemyState;
    //Platforms and Display Screen
    private GameDisplay gameDisplay;
    private PlatformView platformView;
    private Platform platform;
    private double ScreenXsize;
    //Timer
    public static String averageTimer;
    private boolean isgameOver;
    private Handler GameOverDialog;


    public GameView(Context context, int screenHeghit, int screenWidht, Handler GameOverdialog) {
        super(context);
        this.GameOverDialog=GameOverdialog;
        this.isgameOver=false;
        this.screenHeight=screenHeghit;
        SurfaceHolder surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
        this.context=context;
        this.gameLoop=new GameLoop(this,surfaceHolder);
        setFocusable(true);


        //Accurate the Buttons sizes
        int outerRadius = (int) (2 * (screenHeghit / 20));
        int innerRadius = (int) (screenHeghit / 20);
        int jumpRadiusCircle = (int) (screenHeghit / 20);
        float pY = screenHeghit - (screenHeghit / 100 + outerRadius * 3);
        /**joystick Class*/
        this.joystick = new Joystick(screenWidht / 100 + outerRadius * 3, pY, outerRadius, innerRadius, (int) screenHeghit, (int) screenWidht);
        /**jumpBtn Class*/
        this.jumpbtn = new Jumpbtn(context, screenWidht - (screenWidht / 100 + outerRadius * 3), pY, jumpRadiusCircle);
        /**punchBtn Class*/
        this.punchBtn = new PunchBtn(context, screenWidht - (screenWidht / 100 + outerRadius * 2 - 100), pY, jumpRadiusCircle);
        this.ScreenXsize = screenWidht;//Take Size of the Width Screem
        /**HealingBar of ThePlayer*/
        this.healingBarSprite=new HealingBarSprite(context,screenWidht,screenHeghit);
        /**Player Class*/
        //connect the tile map image to the bitmapPlatforms
        PlayerCharacterSprite playerCharacterImage = new PlayerCharacterSprite(context,screenWidht,screenHeghit);
        this.Animator = new AnimatorPlayer(playerCharacterImage.getPlayerSprite());
        this.player = new Player( screenWidht/2, 50,
                this.joystick,
                this.punchBtn,
                this.jumpbtn,
                this.Animator,healingBarSprite,screenWidht);
        this.playerState = new PlayerState(this.player);
        /**Game Display Class*/
        this.gameDisplay = new GameDisplay(getContext(),screenWidht,screenHeghit,this.player);
        /**Platform Class*/
        PlatformSprite platformSprite=new PlatformSprite(context,screenWidht,screenHeghit);
        this.platformView=new PlatformView(platformSprite);
        this.platform=new Platform(screenWidht,screenHeghit,this.player,platformView,screenWidht,gameDisplay);
        /**Enemy Class*/
        this.screenWidht=screenWidht;
        EnemySprite enemySprite=new EnemySprite(context,screenWidht,screenHeghit);
        this.enemyAnimation=new EnemyAnimation(enemySprite.getEnemySprite());
        enemies.add(new Enemy(this.enemyAnimation,this.player,healingBarSprite,platform,screenWidht));
        this.enemyState=new EnemyState(this.enemies.get(0));
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**Touch Event handler for the user*/
        switch (event.getAction()) {

            // when the user click one time on is screen
            case MotionEvent.ACTION_DOWN:
                this.jumpbtn.isPressedJump((double) event.getX(), (double) event.getY());
                this.punchBtn.isPressedPunch((double) event.getX(), (double) event.getY());
                this.enemies.get(0).getHealthBar().setActiviteChangeState(true);

                if (this.joystick.isPressed((double) event.getX(), (double) event.getY())) {

                    this.joystick.setIsPressed(true);

                }
                //when the user click  two times on the same time
            case MotionEvent.ACTION_POINTER_DOWN:

                this.jumpbtn.isPressedJump((double) event.getX(), (double) event.getY());
                if (this.joystick.isPressed((double) event.getX(), (double) event.getY())) {

                    this.joystick.setIsPressed(true);

                }


                //when the player drags is click
            case MotionEvent.ACTION_MOVE:
                if (this.joystick.getIsPressed()) {

                    this.joystick.setActuator((double) event.getX(), (double) event.getY(), ScreenXsize / 2);
                }

                if (this.joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    this.joystick.setIsPressed(true);

                }
                return true;
            //when the player stop the click on the screen
            case MotionEvent.ACTION_UP:
                //when the player stop the two clicks on the screen
            case MotionEvent.ACTION_POINTER_UP:
                this.joystick.resetActuator();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        //drawing the game

        super.draw(canvas);
        this.gameDisplay.drawBackGround(canvas);
        this.platform.Draw(canvas);
        Timer(canvas);
        drawFPS(canvas);
        this.jumpbtn.draw(canvas);
        this.punchBtn.draw(canvas);
        this.joystick.draw(canvas);
        this.player.Draw(canvas);
        this.enemies.get(0).Draw(canvas);
    }

    //draw Frame per second
    private void drawFPS(Canvas canvas) {
        //showing frame per second
        String averagefps = Integer.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();

        int color = ContextCompat.getColor(getContext(), R.color.white);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("FPS :" + averagefps, 1, 100, paint);
    }
    private  void Timer(Canvas canvas){
        this.averageTimer = gameLoop.getTimeInMinute() + " : " +gameLoop.getTimeInSecond() ;
        Paint paint = new Paint();

        int color = ContextCompat.getColor(getContext(), R.color.white);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("Timer :" + averageTimer, (screenWidht/2)-averageTimer.length()*40,50 , paint);
    }
    public String getTimer(){
        return averageTimer;
    }

    public void update() {
        //update the logics and the moves of the player and the enemy
        this.gameDisplay.update();
        this.joystick.update();
        if(Enemy.readyToSpawn(enemies.get(0).getHealthBar()) || enemies.get(0).getPositionY()>=screenWidht){
            enemies.remove(0);
            enemies.add(new Enemy(this.enemyAnimation,this.player,healingBarSprite,platform,screenWidht));

        }
        for(Enemy enemy :enemies){
            enemy.update(player,platform);
        }
        this.player.update(enemies.get(0),this.platform);

        this.platform.update(null,this.player);
        //punch and dell damage between enemy and player
        if(player.getPunchBtn().isPunch() && player.getHeightImage()>enemies.get(0).getDistanceBetWeenObjectToEnemy() && enemies.get(0).getHealthBar().isActiviteChangeState()){

            if(this.enemies.get(0).getVelocityX()>0)
                this.enemies.get(0).getEnemyState().setState(EnemyState.State.DAMAGED_RIGHT);
            else
                this.enemies.get(0).getEnemyState().setState(EnemyState.State.DAMAGED_LEFT);
            //in the Range
           //
            switch (this.enemies.get(0).getHealthBar().getHealthBarState().getState()){

                case FULL_HP:
                    this.enemies.get(0).getHealthBar().getHealthBarState().SetState(HealthBarState.State.ABOUT_FULL_HP);
                    enemies.get(0).getHealthBar().setActiviteChangeState(false);
                    enemies.get(0).getEnemyState().setState(EnemyState.State.DAMAGED_RIGHT);
                    break;
                case ABOUT_FULL_HP:
                    this.enemies.get(0).getHealthBar().getHealthBarState().SetState(HealthBarState.State.HALF_HP);
                    enemies.get(0).getHealthBar().setActiviteChangeState(false);
                    break;
                case HALF_HP:
                    this.enemies.get(0).getHealthBar().getHealthBarState().SetState(HealthBarState.State.LOW_HP);
                    enemies.get(0).getHealthBar().setActiviteChangeState(false);
                    break;
                case LOW_HP:
                    this.enemies.get(0).getHealthBar().getHealthBarState().SetState(HealthBarState.State.NON_HP);
                    break;
            }
        }
        if(this.enemies.get(0).isOnCollision() && this.enemies.get(0).getEnemyState().isPunched() && this.player.getHealthBar().getHealthBarState().getState()!= HealthBarState.State.NON_HP){
            if(this.player.getVelocityX()>0)
                this.player.getPlayerState().SetState(PlayerState.State.DAMAGED_RIGHT);
            else
                this.player.getPlayerState().SetState(PlayerState.State.DAMAGED_LEFT);
            switch (this.player.getHealthBar().getHealthBarState().getState()){
                case  FULL_HP:
                    this.player.getHealthBar().getHealthBarState().SetState(HealthBarState.State.ABOUT_FULL_HP);
                    this.enemies.get(0).getEnemyState().setPunched(false);
                    break;
                case  ABOUT_FULL_HP:
                    this.player.getHealthBar().getHealthBarState().SetState(HealthBarState.State.HALF_HP);
                    this.enemies.get(0).getEnemyState().setPunched(false);
                    break;
                case  HALF_HP:
                    this.player.getHealthBar().getHealthBarState().SetState(HealthBarState.State.LOW_HP);
                    this.enemies.get(0).getEnemyState().setPunched(false);
                    break;
                case  LOW_HP:
                    this.player.getHealthBar().getHealthBarState().SetState(HealthBarState.State.NON_HP);
                    this.enemies.get(0).getEnemyState().setPunched(false);
                case NON_HP:
                    this.isgameOver=true;
                    break;

            }
        }
        if(player.getPositionY()>=screenHeight){
            isgameOver=true;
        }

    }
    /**TheCollision Between the player to the enemy*/


    public boolean isIsgameOver() {
        return isgameOver;
    }


    public Handler getGameOverDialog() {
        return GameOverDialog;
    }
}


