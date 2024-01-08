package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Order;

/** Generates the orders for the customers  */
public class OrderController {
    private final RecipeController recipeController;

    public OrderController(RecipeController recipes){
        recipeController = recipes;
    }

    /**
     * Generates a new Order which contains 1 recipe
     * <p>
     * @return the finished order
     */
    public Order generateNewOrder() {
        return new Order(recipeController.getRandomRecipe());
    }
}
