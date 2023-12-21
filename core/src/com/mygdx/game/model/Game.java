package com.mygdx.game.model;

/**
 * This class sets the game's goal and initial settings
 */
public class Game {
    public float timeLeft = 0;
    public float timeLeftLastFrame = 0;

    public int payTotal = 0;
    public int payGoal = 0;

    public float customerSpawnChance = 0;

    public Game(float initialTime, int payGoal, float initialCustomerSpawnChance) {
        this.timeLeft = initialTime;
        this.payGoal = payGoal;
        this.customerSpawnChance = initialCustomerSpawnChance;
    }
}
