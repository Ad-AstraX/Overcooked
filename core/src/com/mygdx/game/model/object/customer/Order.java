package com.mygdx.game.model.object.customer;

/** This class represents an order containing a recipes that the player must recreate and serve to the customer */
public class Order {
    private final Recipe recipe;

    public Order(Recipe recipes) {
        this.recipe = recipes;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
