package com.example.movementplayer.StateObject;

import android.os.Handler;
import android.util.Log;

import com.example.movementplayer.Buttens.PunchBtn;
import com.example.movementplayer.Objects.Enemy;
import com.example.movementplayer.Objects.GameObject;
import com.example.movementplayer.Objects.Player;

public class EnemyState {


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




    }
    private Enemy enemy;
    private EnemyState.State state;
    private boolean Punched;





    public EnemyState(Enemy enemy){
        //register verables
        this.enemy=enemy;
        this.state= EnemyState.State.NOT_MOVING_RIGHT;


    }
    //the situation of the player
    public void updateMoving(){
        switch (state){
            case NOT_MOVING_RIGHT://when the player not moving
                if(this.enemy.getVelocityX()>0){
                    state= EnemyState.State.START_MOVING_RIGHT;//start player moves
                }
                else if(this.enemy.getVelocityX()<0){
                    state= EnemyState.State.START_MOVING_LEFT;
                }
                if(enemy.getVelocityX()==0 &&  enemy.getlastlocation()>0 &&  enemy.isOnCollision()) {
                    state = EnemyState.State.START_PUNCH_RIGHT;
                }


            case NOT_MOVING_LEFT://when the player not moving
                if(this.enemy.getVelocityX()<0 ){
                    state= EnemyState.State.START_MOVING_LEFT;//start player moves
                }
                else if(this.enemy.getVelocityX()>0){
                    state= EnemyState.State.START_MOVING_RIGHT;//start player moves
                }
                if(enemy.getVelocityX()==0 &&  enemy.getlastlocation()<0 && enemy.isOnCollision() ) {
                    state = EnemyState.State.START_PUNCH_LEFT;
                }
                break;

            case START_MOVING_RIGHT://when the player start move
                if(this.enemy.getVelocityX()>0 ) {
                    state = EnemyState.State.IS_MOVING_RIGHT;//check if he is moving
                }
                else if(this.enemy.getlastlocation()>0 && this.enemy.getVelocityX()==0)
                    state = State.NOT_MOVING_RIGHT;
                if(this.enemy.getVelocityX()<0){
                    state=State.START_MOVING_LEFT;
                }

                break;

            case START_MOVING_LEFT://when the player start move
                if(this.enemy.getVelocityX()<0) {
                    state = EnemyState.State.IS_MOVING_LEFT;//check if he is moving
                }
                else if(this.enemy.getlastlocation()<0 && this.enemy.getVelocityX()==0)
                    state = EnemyState.State.NOT_MOVING_LEFT;

                if(this.enemy.getVelocityX()>0)
                    state=State.START_MOVING_RIGHT;
                break;

            case IS_MOVING_RIGHT://when the player on the move
                //check player if he is stop to move
                if(this.enemy.getlastlocation()>0 && this.enemy.getVelocityX()==0)
                    state = EnemyState.State.NOT_MOVING_RIGHT;

                else if(this.enemy.getVelocityX()<0 )
                    state = EnemyState.State.START_MOVING_LEFT;
                break;

            case IS_MOVING_LEFT://when the player on the move
                //check player if he is stop to move
                if(this.enemy.getlastlocation()<=0 && this.enemy.getVelocityX()==0)
                    state = EnemyState.State.NOT_MOVING_LEFT;
                else if(this.enemy.getVelocityX()>0)
                    state = EnemyState.State.START_MOVING_RIGHT;
                break;
            case START_PUNCH_LEFT:
                    if(enemy.isOnCollision() && this.enemy.getlastlocation()<0){
                        state= State.IS_PUNCH_LEFT;
                    }
                break;
            case START_PUNCH_RIGHT:
                if(enemy.isOnCollision() && this.enemy.getlastlocation()>0){
                    state= State.IS_PUNCH_RIGHT;
                }
                break;
            case IS_PUNCH_LEFT:
                if(!enemy.isOnCollision() && this.enemy.getlastlocation()<0){
                    state=State.NOT_MOVING_LEFT;
                }
                break;
            case IS_PUNCH_RIGHT:
                if(!enemy.isOnCollision() && this.enemy.getlastlocation()>0){
                    state=State.NOT_MOVING_RIGHT;
                }
                break;
            case DAMAGED_LEFT:
                state=State.NOT_MOVING_LEFT;
            case DAMAGED_RIGHT:
                state=State.NOT_MOVING_RIGHT;

            default:
                break;
        }
    }
    public EnemyState.State getState(){
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPunched(boolean test) {
        Punched = test;
    }

    public boolean isPunched() {
        return Punched;
    }
}
