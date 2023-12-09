package com.mygdx.game.model;

public class GameState {
    public float timeLeft = 0;
    public int totalPay = 0;
    public int payGoal = 0;

    public GameState(float time, int payGoal) {
        this.timeLeft = time;
        this.payGoal = payGoal;
    }
}
