package com.mygdx.game.model.object.customer;

import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/** This class represents an order containing a recipes that the player must recreate and serve to the customer */
public class Order {
    /** The recipe stored in this order */
    private final Stack<Ingredient> recipe;

    public Order(Stack<Ingredient> recipe) {
        this.recipe = recipe;
    }

    // All Getters
    public Stack<Ingredient> getRecipe() {
        return recipe;
    }
}
