package com.mygdx.game.model;

public class Game {
    public float timeleft = 0;
    public int totalPay = 0;
    public int payGoal = 0;

    public Game(float time, int payGoal) {
        this.timeleft = time;
        this.payGoal = payGoal;
    }
}
