package com.example.movementplayer.Animation;

import android.graphics.Canvas;

import com.example.movementplayer.ImageSnippingTool.EnemyView;
import com.example.movementplayer.ImageSnippingTool.PlayerCharacter;
import com.example.movementplayer.Objects.Enemy;
import com.example.movementplayer.Objects.Player;
import com.example.movementplayer.Sprite.EnemySprite;

public class EnemyAnimation {
    private  static final int MAX_UPDATE_MOVING_FRAME=10;
    private  int idxMovingRightFrames;
    private  int idxMovingLeftFrames;
    private  int idxMovingidleFrames;
    private  int idxMovingidleLeftFrames;
    private  int updateBeforeNext;
    private  int idxstartpunchRight;
    private  int idxstartpunchLeft;
    private  int idxstartDamagedRight;
    private  int idxstartDamagedLeft;

    private EnemyView[] EnemySpritesArray;
    public EnemyAnimation (EnemyView[] EnemyArt) {
        this.idxMovingRightFrames=8;
        this.idxMovingLeftFrames=15;
        this.idxMovingidleFrames=0;
        this.idxMovingidleLeftFrames=14;
        this.updateBeforeNext=10;
        this.idxstartpunchRight=20;
        this.idxstartpunchLeft=24;
        this.idxstartDamagedRight=29;
        this.idxstartDamagedLeft=34;
        EnemySpritesArray=EnemyArt;
    }

    private void drawFrame(Canvas canvas, int positionX, int positionY, EnemyView EnemySprite) {
        EnemySprite.draw(canvas,(int)positionX, (int)positionY);

    }
    /**draw the animation of the player,determines by playerStates*/
    public void draw(Canvas canvas, int positionX, int positionY, Enemy enemy) {

        switch (enemy.getEnemyState().getState()) {

            case NOT_MOVING_RIGHT:
                this.updateBeforeNext--;
                if (this.updateBeforeNext == 0) {
                    this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                    toggleIdxIdleFrame();

                }
                drawFrame(canvas, positionX, positionY, this.EnemySpritesArray[idxMovingidleFrames]);

                break;

            case NOT_MOVING_LEFT:
                this.updateBeforeNext--;
                if (this.updateBeforeNext == 0) {
                    this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                    toggleIdxIdleLeftFrame();

                }
                drawFrame(canvas, positionX, positionY, EnemySpritesArray[idxMovingidleLeftFrames]);
                break;

            case START_MOVING_RIGHT:

                this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas, positionX, positionY, EnemySpritesArray[idxMovingRightFrames]);
                break;

            case START_MOVING_LEFT:
                this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas, positionX, positionY, EnemySpritesArray[idxMovingLeftFrames]);
                break;

            case IS_MOVING_RIGHT:
                this.updateBeforeNext--;
                if (this.updateBeforeNext == 4) {
                    this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                    toggleIdxMovingFrame();
                }
                drawFrame(canvas, positionX, positionY, EnemySpritesArray[idxMovingRightFrames]);
                break;
            case IS_MOVING_LEFT:
                this.updateBeforeNext--;
                if (this.updateBeforeNext == 0) {
                    this.updateBeforeNext = MAX_UPDATE_MOVING_FRAME;
                    toggleMovingLeftFrame();
                }
                drawFrame(canvas, positionX, positionY, EnemySpritesArray[idxMovingLeftFrames]);
                break;

            case START_PUNCH_RIGHT:
                updateBeforeNext=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartpunchRight]);
                break;
            case IS_PUNCH_RIGHT:
                updateBeforeNext--;
                if(updateBeforeNext==0){
                    updateBeforeNext=MAX_UPDATE_MOVING_FRAME;
                    togglePunchRightFrames(enemy);
                }
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartpunchRight]);
                break;
            case START_PUNCH_LEFT:
                updateBeforeNext =MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartpunchLeft]);
                break;
            case IS_PUNCH_LEFT:
                updateBeforeNext--;
                if(updateBeforeNext==0){
                    updateBeforeNext=MAX_UPDATE_MOVING_FRAME;
                    togglePunchLeftFrames(enemy);
                }
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartpunchLeft]);
                break;

            case DAMAGED_RIGHT:
                updateBeforeNext--;
                if(updateBeforeNext==0){
                    updateBeforeNext=MAX_UPDATE_MOVING_FRAME;
                    toggleDamageFramesRight();
                }
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartDamagedRight]);
                break;
            case DAMAGED_LEFT:
                updateBeforeNext--;
                if(updateBeforeNext==0){
                    updateBeforeNext=MAX_UPDATE_MOVING_FRAME;
                    toggleDamageFramesLeft();
                }
                drawFrame(canvas,positionX,positionY, EnemySpritesArray[idxstartDamagedLeft]);
                break;


        }

    }
    private void toggleDamageFramesRight(){
        switch (idxstartDamagedRight){
            case 29:
                idxstartDamagedRight=30;
                break;
            case 30:
                idxstartDamagedRight=31;
                break;
            case 31:
                idxstartDamagedRight=32;
                break;
            case 32:
                idxstartDamagedRight=33;
                break;
            case 33:
                idxstartDamagedRight=29;
                break;
        }
    }
    private void toggleDamageFramesLeft(){
        switch (idxstartDamagedLeft){
            case 34:
                idxstartDamagedLeft=35;
                break;
            case 35:
                idxstartDamagedLeft=36;
                break;
            case 36:
                idxstartDamagedLeft=37;
                break;
            case 37:
                idxstartDamagedLeft=34;
                break;
        }
    }
    private void togglePunchLeftFrames(Enemy enemy) {
        switch (idxstartpunchLeft) {
            case 24:
                idxstartpunchLeft = 25;
                break;
            case 25:
                idxstartpunchLeft = 26;
            case 26:
                idxstartpunchLeft = 27;
                break;
            case 27:
                enemy.getEnemyState().setPunched(true);
                idxstartpunchLeft = 24;
            default:
                break;
        }
    }

    private void togglePunchRightFrames(Enemy enemy) {
        switch (idxstartpunchRight){
            case 20:
                idxstartpunchRight=21;
                break;
            case 21:
                idxstartpunchRight=22;
            case 22:
                idxstartpunchRight=23;
                break;
            case 23:
                enemy.getEnemyState().setPunched(true);
                idxstartpunchRight=20;
            default:
                break;
        }
    }

    /**moving animation on right side */
    private void toggleIdxMovingFrame() {
        switch (idxMovingRightFrames){
            case 8:
                idxMovingRightFrames=9;
                break;
            case 9:
                idxMovingRightFrames=10;
                break;
            case 10:
                idxMovingRightFrames=11;
                break;
            case 11:
                idxMovingRightFrames=12;
            case 12:
                idxMovingRightFrames=13;
                break;
            case 13:
                idxMovingRightFrames=8;
                break;
            default:
                break;
        }
    }
    /**not moving animation on right side*/
    private void toggleIdxIdleFrame() {

        switch (idxMovingidleFrames) {
            case 0:
                idxMovingidleFrames = 1;
                break;
            case 1:
                idxMovingidleFrames = 2;
                break;
            case 2:
                idxMovingidleFrames = 3;
                break;
            case 3:
                idxMovingidleFrames = 0;
                break;
            default:
                break;
        }
    }
    /** not moving animation on left side */
    private void toggleIdxIdleLeftFrame() {

        switch (idxMovingidleLeftFrames) {
            case 14:
                idxMovingidleLeftFrames = 15;
                break;
            case 15:
                idxMovingidleLeftFrames = 16;
                break;
            case 16:
                idxMovingidleLeftFrames = 17;
                break;
            case 17:
                idxMovingidleLeftFrames = 18;
                break;
            case 18:
                idxMovingidleLeftFrames = 19;
                break;
            case 19:
                idxMovingidleLeftFrames = 14;
                break;
            default:
                break;
        }
    }
    /**moving animation on left side */
    private void toggleMovingLeftFrame() {
        switch (idxMovingLeftFrames) {
            case 15:
                idxMovingLeftFrames = 14;
                break;
            case 14:
                idxMovingLeftFrames = 13;
                break;
            case 13:
                idxMovingLeftFrames = 12;
                break;
            case 12:
                idxMovingLeftFrames = 11;
                break;
            case 11:
                idxMovingLeftFrames = 10;
                break;
            case 10:
                idxMovingLeftFrames = 15;
                break;
            default:
                break;
        }

    }
    public float getcharacterSize() {
        return EnemySpritesArray[0].getHeight();
    }
}
