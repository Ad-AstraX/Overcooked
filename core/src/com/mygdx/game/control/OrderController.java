package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Order;
import com.mygdx.game.model.object.customer.Recipe;

public class OrderController {
    private RecipeController recipeController;

    public OrderController(RecipeController recipes){
        recipeController = recipes;
    }

    /**
     * Generates a new Order which contains 1-3 recipes
     * <p>
     * @param recipeCount the amount of recipes this order has
     * @return the finished order
     */
    public Order generateNewOrder(int recipeCount) {
        if (recipeCount > 3)
            recipeCount = 3;

        Recipe[] recipes = new Recipe[recipeCount];

        for (int i = 0; i < recipeCount; i++)
            recipes[i] = recipeController.getRandomRecipe();

        return new Order(recipes);
    }
}
