package com.mygdx.game.control;

import com.mygdx.game.model.Recipe;

public class RecipeController {
    private static final Recipe[] ALL_RECIPES = {
            new Recipe(),
            new Recipe()
    };

    public Recipe getRandomRecipe() {
        int index = (int)(Math.random() * ALL_RECIPES.length);
        return ALL_RECIPES[index];
    }
}
