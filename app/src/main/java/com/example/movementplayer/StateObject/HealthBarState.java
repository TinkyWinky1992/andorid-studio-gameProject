package com.example.movementplayer.StateObject;

import com.example.movementplayer.Objects.GameObject;
import com.example.movementplayer.Sprite.HealingBarSprite;

public class HealthBarState {
    public enum State {
        //Player heating point
        FULL_HP,//1
        ABOUT_FULL_HP,//2
        HALF_HP,//3
        LOW_HP,//4
        NON_HP,//5


    }

    private GameObject HealthStateObject;
    private HealthBarState.State state;
    private boolean ischange;
    public HealthBarState(GameObject gameObject) {
        //register verables
        this.HealthStateObject = gameObject;
        this.state = State.FULL_HP;
        this.ischange=false;
    }


    public void setIschange(boolean ischange) {
        this.ischange = ischange;
    }

    public boolean isChange() {
        return ischange;
    }

    public HealthBarState.State getState(){
        return state;
    }
    public void SetState(State state){
        this.state=state;
    }
}


