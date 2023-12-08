package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Recipe;

public class RecipeController {
    private static final Recipe[] ALL_RECIPES = {
            new Recipe(0),
            new Recipe(0)
    };

    public Recipe getRandomRecipe() {
        int index = (int)(Math.random() * ALL_RECIPES.length);
        return ALL_RECIPES[index];
    }
}
