package com.mygdx.game.model;

/**
 * This class sets the game's goal and initial settings
 */
public class Game {
    private float timeLeft;
    private float timeLeftLastFrame = 0;

    private int payTotal = 0;
    private int payGoal;

    private float customerSpawnChance;

    public Game(float initialTime, int payGoal, float initialCustomerSpawnChance) {
        this.timeLeft = initialTime;
        this.payGoal = payGoal;
        this.customerSpawnChance = initialCustomerSpawnChance;
    }

    // All Getters
    public float getTimeLeft() {
        return timeLeft;
    }
    public float getTimeLeftLastFrame() {
        return timeLeftLastFrame;
    }
    public int getPayTotal() {
        return payTotal;
    }
    public int getPayGoal() {
        return payGoal;
    }
    public float getCustomerSpawnChance() {
        return customerSpawnChance;
    }

    // All Setters
    public void setTimeLeft(float timeLeft) {
        this.timeLeft = timeLeft;
    }
    public void setTimeLeftLastFrame(float timeLeftLastFrame) {
        this.timeLeftLastFrame = timeLeftLastFrame;
    }
    public void setPayTotal(int payTotal) {
        this.payTotal = payTotal;
    }
    public void setPayGoal(int payGoal) {
        this.payGoal = payGoal;
    }
    public void setCustomerSpawnChance(float customerSpawnChance) {
        this.customerSpawnChance = customerSpawnChance;
    }
}
