package com.mygdx.game.model.object.customer;

/**
 * This class represents an order with a set amount of recipes that the player must recreate and serve
 */
public class Order {
    private Recipe[] recipes;

    public Order(Recipe[] recipes) {
        this.recipes = recipes;
    }
}
