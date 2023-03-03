package com.example.movementplayer.StateObject;

import com.example.movementplayer.Animation.AnimatorPlayer;
import com.example.movementplayer.Buttens.PunchBtn;
import com.example.movementplayer.Objects.GameObject;
import com.example.movementplayer.Objects.HealthBar;
import com.example.movementplayer.Objects.Player;
import android.os.Handler;
import android.util.Log;

/**Manage the player State to decide which Sprite Image run for the player*/
public class PlayerState {

    //states of the player
    public enum State{
        //moving stastes
        NOT_MOVING_RIGHT,
        NOT_MOVING_LEFT,
        START_MOVING_LEFT,
        START_MOVING_RIGHT,
        IS_MOVING_LEFT,
        IS_MOVING_RIGHT,
        //punch right
        IS_PUNCH_RIGHT,
        START_PUNCH_RIGHT,
        //punch left
        START_PUNCH_LEFT,
        IS_PUNCH_LEFT,
        //damaged
        DAMAGED_RIGHT,
        DAMAGED_LEFT,
        //Death
        START_DEATH_RIGHT,
        IS_DEATH_RIGHT,

        START_DEATH_LEFT,
        IS_DEATH_LEFT,




    }
    private Player gameObjectState;
    private State state;
    //TO save the last state



    public PlayerState(Player Object){
        //register verables
        this.gameObjectState=Object;
        this.state=State.NOT_MOVING_RIGHT;

    }
    //the situation of the player
    public void updateMoving(PunchBtn punchBtn, HealthBar healthBar, AnimatorPlayer animatorPlayer){
        switch (state){
            case NOT_MOVING_RIGHT://when the player not moving
                if(healthBar.getHealthBarState().isChange() && this.gameObjectState.getVelocityX()>0  ){
                    state =State.DAMAGED_RIGHT;
                }
                if(healthBar.getHealthBarState().isChange() && this.gameObjectState.getVelocityX()<0  ){
                    state =State.DAMAGED_LEFT;
                }
                if(this.gameObjectState.getVelocityX()>0 ){
                    state=State.START_MOVING_RIGHT;//start player moves
                }
                else if(this.gameObjectState.getVelocityX()<0 ){
                    state=State.START_MOVING_LEFT;
                }
                if(gameObjectState.getVelocityX()==0 && punchBtn.isPunch() && gameObjectState.getlastlocation()>0) {
                    state = State.START_PUNCH_RIGHT;
                }


            break;

            case NOT_MOVING_LEFT://when the player not moving
                if(this.gameObjectState.getVelocityX()<0 ){
                    state=State.START_MOVING_LEFT;//start player moves
                }
               else if(this.gameObjectState.getVelocityX()>0){
                    state=State.START_MOVING_RIGHT;//start player moves
                }
               if(gameObjectState.getVelocityX()==0 && punchBtn.isPunch() && gameObjectState.getlastlocation()<0){
                    state=State.START_PUNCH_LEFT;
                }

                break;

            case START_MOVING_RIGHT://when the player start move
                if(this.gameObjectState.getVelocityX()>0 || this.gameObjectState.getVelocityY()!=0) {
                    state = State.IS_MOVING_RIGHT;//check if he is moving
                }
                break;

            case START_MOVING_LEFT://when the player start move
                if(this.gameObjectState.getVelocityX()<0 || this.gameObjectState.getVelocityY()!=0) {
                    state = State.IS_MOVING_LEFT;//check if he is moving
                }

                break;

            case IS_MOVING_RIGHT://when the player on the move
                //check player if he is stop to move
                if(this.gameObjectState.getlastlocation()>0 && this.gameObjectState.getVelocityX()==0)
                    state = State.NOT_MOVING_RIGHT;

                else if(this.gameObjectState.getVelocityX()<0 )
                    state =State.START_MOVING_LEFT;
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()>0){
                    this.state=State.START_DEATH_RIGHT;
                }
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()<0){
                    this.state=State.START_DEATH_LEFT;
                }
                break;

            case IS_MOVING_LEFT://when the player on the move
                //check player if he is stop to move
                if(this.gameObjectState.getlastlocation()<0 && this.gameObjectState.getVelocityX()==0 )
                    state =State.NOT_MOVING_LEFT;
                else if(this.gameObjectState.getVelocityX()>0)
                    state =State.START_MOVING_RIGHT;
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()>0){
                    this.state=State.START_DEATH_RIGHT;
                }
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()<0){
                    this.state=State.START_DEATH_LEFT;
                }
                break;
                /**Punching Frames*/

            case START_PUNCH_RIGHT:
                if(punchBtn.isPunch() && gameObjectState.getlastlocation()>0){
                    state = State.IS_PUNCH_RIGHT;//when the user click on the button
                }

                break;


            case START_PUNCH_LEFT:
                if(punchBtn.isPunch() && gameObjectState.getlastlocation()<0){
                    state = State.IS_PUNCH_LEFT;//when the user click on the button
                }
            case IS_PUNCH_RIGHT:
                if( punchBtn.isPunch() && gameObjectState.getVelocityX()>0){
                    this.state=State.NOT_MOVING_RIGHT;
                }
            case IS_PUNCH_LEFT:
                if( punchBtn.isPunch() && gameObjectState.getVelocityX()<0){
                    this.state=State.NOT_MOVING_LEFT;
                }
                break;
            case DAMAGED_RIGHT:
                    this.state=State.NOT_MOVING_RIGHT;
                    if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()>0){
                        this.state=State.START_DEATH_RIGHT;
                    }
                     if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()<0){
                        this.state=State.START_DEATH_LEFT;
                    }


                break;
            case DAMAGED_LEFT:
                    this.state=State.NOT_MOVING_LEFT;
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()>0){
                    this.state=State.START_DEATH_RIGHT;
                }
                if(gameObjectState.getHealthBar().getHealthBarState().getState()== HealthBarState.State.NON_HP && gameObjectState.getlastlocation()<0){
                    this.state=State.START_DEATH_LEFT;
                }

              break;
            case START_DEATH_LEFT:
                this.state=State.IS_DEATH_LEFT;
            case START_DEATH_RIGHT:
                this.state=State.IS_DEATH_RIGHT;
            /**Dead*/

            default:
                break;
        }
    }

    public void SetState(PlayerState.State state){
        this.state=state;
    }
    public State getState(){
        return state;
    }

}
