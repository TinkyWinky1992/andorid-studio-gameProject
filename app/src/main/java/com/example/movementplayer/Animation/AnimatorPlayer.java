package com.example.movementplayer.Animation;

import android.graphics.Canvas;
import android.util.Log;

import com.example.movementplayer.ImageSnippingTool.PlayerCharacter;
import com.example.movementplayer.Objects.Enemy;
import com.example.movementplayer.Objects.Player;
import com.example.movementplayer.StateObject.PlayerState;

/**Manage the Animation player*/
//We Manage the animation by the first image index in the  Image tail set of the player
public class AnimatorPlayer {
        private  int idxMovingRightFrames;
        private  int idxMovingLeftFrames;
        private  int idxMovingidleFrames;
        private  int idxMovingidleLeftFrames;
        private  int updateBeforeNextMove;
        private int idxstartpunchRight;
        private int idxstartpunchLeft;
        private int idxDamgeRight;
        private int idxDamgeLeft;
        private int idxDeathRight;
        private int idxDeathLeft;

        private  static final int MAX_UPDATE_MOVING_FRAME=5;
        private PlayerCharacter[] playerCharactersArray;

        public AnimatorPlayer (PlayerCharacter[] playerArt) {
            //starting the idx variable with the start image frame in the array
            this.idxMovingRightFrames=4;
            this.idxMovingLeftFrames=15;
            this.idxMovingidleFrames=0;
            this.idxMovingidleLeftFrames=19;
            this.updateBeforeNextMove=10;
            this.idxstartpunchRight=21;
            this.idxstartpunchLeft=29;
            this.idxDamgeRight=37;
            this.idxDamgeLeft=39;
            this.idxDeathRight=41;
            this.idxDeathLeft=47;
            this.playerCharactersArray=playerArt;
        }

    private void drawFrame(Canvas canvas, int positionX, int positionY,PlayerCharacter playerCharacter) {
        playerCharacter.draw(canvas,(int)positionX, (int)positionY);

    }
    /**draw the animation of the player,determines by playerStates*/
    public void draw(Canvas canvas, int positionX, int positionY,Player player) {

        switch (player.getPlayerState().getState() ){

            case NOT_MOVING_RIGHT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleIdxIdleFrame();

                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingidleFrames]);

                break;

            case NOT_MOVING_LEFT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleIdxIdleLeftFrame();

                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingidleLeftFrames]);
                break;

            case START_MOVING_RIGHT:

                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingRightFrames]);
                break;

            case START_MOVING_LEFT:
                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingLeftFrames]);
                break;

            case IS_MOVING_RIGHT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleIdxMovingFrame();
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingRightFrames]);
                break;
            case IS_MOVING_LEFT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleMovingLeftFrame();
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxMovingLeftFrames]);
                break;
            case START_PUNCH_RIGHT:

                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxstartpunchRight]);
                break;
            case IS_PUNCH_RIGHT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    togglePuchRightFrames(player);
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxstartpunchRight]);
                break;
            case START_PUNCH_LEFT:
                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxstartpunchLeft]);
                break;
            case IS_PUNCH_LEFT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    togglePunchLeftFrames(player);
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxstartpunchLeft]);
                break;
            case DAMAGED_RIGHT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleDamageRight(player);
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDamgeRight]);
                break;
            case DAMAGED_LEFT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleDamageLeft(player);
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDamgeLeft]);
                break;
            case START_DEATH_RIGHT:
                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDeathRight]);
                break;
            case START_DEATH_LEFT:
                updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDeathLeft]);
                break;
            case IS_DEATH_RIGHT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleDeathRight();
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDeathRight]);
                break;
            case IS_DEATH_LEFT:
                updateBeforeNextMove--;
                if(updateBeforeNextMove==0){
                    updateBeforeNextMove=MAX_UPDATE_MOVING_FRAME;
                    toggleDeathLeft();
                }
                drawFrame(canvas,positionX,positionY,playerCharactersArray[idxDeathLeft]);
                break;
        }

    }

    private void toggleDeathRight() {
        switch (idxDeathRight) {
            case 41:
                idxDeathRight = 42;
                break;
            case 42:
                idxDeathRight = 43;
                break;
            case 43:
                idxDeathRight = 44;
                break;
            case 44:
                idxDeathRight = 45;
                break;
            default:
                break;
        }

    }

    private void toggleDeathLeft() {
        switch (idxDeathLeft) {
            case 47:
                idxDeathLeft = 48;
                break;
            case 48:
                idxDeathLeft = 49;
                break;
            case 49:
                idxDeathLeft = 50;
                break;
            case 50:
                idxDeathLeft = 51;
                break;
            case 51:
                idxDeathLeft = 52;
                break;
            default:
                break;
        }
    }


    /**draw the punch animation of the player,determines by playerStates*/


    /**moving animation on right side */
    private void toggleDamageRight(Player player) {
        switch (idxDamgeRight){
            case 37:
                idxDamgeRight=38;
                break;
            case 38:
                idxDamgeRight=37;
                player.getHealthBar().getHealthBarState().setIschange(false);
        }
    }
    private void toggleDamageLeft(Player player) {
        switch (idxDamgeLeft){
            case 39:
                idxDamgeLeft=40;
                break;
            case 40:
                idxDamgeLeft=39;
                player.getHealthBar().getHealthBarState().setIschange(false);

        }
    }
    private void toggleIdxMovingFrame() {
        switch (idxMovingRightFrames){
            case 4:
                idxMovingRightFrames=5;
                break;
            case 5:
                idxMovingRightFrames=6;
                break;
            case 6:
                idxMovingRightFrames=7;
                break;
            case 7:
                idxMovingRightFrames=8;
                break;
            case 8:
                idxMovingRightFrames=9;
                break;
            case 9:
                idxMovingRightFrames=4;
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
            case 19:
                idxMovingidleLeftFrames = 18;
                break;
            case 18:
                idxMovingidleLeftFrames = 17;
                break;
            case 17:
                idxMovingidleLeftFrames = 16;
                break;
            case 16:
                idxMovingidleLeftFrames = 19;
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
    private void togglePuchRightFrames(Player player) {
        switch (idxstartpunchRight ) {
            case 21:
                idxstartpunchRight=22;
                break;
            case 22:
                idxstartpunchRight=23;
                break;
            case 23:
                idxstartpunchRight=24;
                break;
            case 24:
                idxstartpunchRight=25;
                break;
            case 25:
                idxstartpunchRight=26;
                break;
            case 26:
                idxstartpunchRight=27;
                break;
            case 27:
                idxstartpunchRight=28;
                break;
            case 28:
                idxstartpunchRight=21;
                player.getPunchBtn().setisPunch(false);
                player.getPlayerState().SetState(PlayerState.State.NOT_MOVING_RIGHT);
                break;
                }
            }
    private void togglePunchLeftFrames(Player player) {

        switch (idxstartpunchLeft) {
            case 29:
                idxstartpunchLeft=30;
                break;
            case 30:
                idxstartpunchLeft=31;
                break;
            case 31:
                idxstartpunchLeft=32;
                break;
            case 32:
                idxstartpunchLeft=33;
                break;
            case 33:
                idxstartpunchLeft=34;
                break;
            case 34:
                idxstartpunchLeft=35;
                break;
            case 35:
                idxstartpunchLeft=36;
                break;
            case 36:
                idxstartpunchLeft=29;
                player.getPunchBtn().setisPunch(false);
                player.getPlayerState().SetState(PlayerState.State.NOT_MOVING_LEFT);
                break;
        }
    }


    public float getcharacterSize() {
            return playerCharactersArray[0].getHeight();
    }
}
