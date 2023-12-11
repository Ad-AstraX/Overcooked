package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Order;
import com.mygdx.game.model.object.customer.Recipe;

public class OrderController {
    private RecipeController recipeController;

    public OrderController(RecipeController recipes){
        recipeController = recipes;
    }

    public Order generateNewOrder(int recipeCount) {
        Recipe[] recipes = new Recipe[recipeCount];

        for (int i = 0; i < recipeCount; i++)
            recipes[i] = recipeController.getRandomRecipe();

        return new Order(recipes);
    }
}
