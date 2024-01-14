package com.mygdx.game.model.object.customer;

/** This class represents an order containing a recipes that the player must recreate and serve to the customer */
public class Order {
    /** The recipe stored in this order */
    private final Recipe recipe;

    public Order(Recipe recipes) {
        this.recipe = recipes;
    }

    // All Getters
    public Recipe getRecipe() {
        return recipe;
    }
}
