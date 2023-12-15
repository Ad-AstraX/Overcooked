package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Recipe;
import com.mygdx.game.model.object.holdable.ingredient.*;

public class RecipeController {
    private static final Recipe[] ALL_RECIPES = {
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Tomato(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Tomato(), new Salad(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Sauce(), new Tomato(), new Salad(), new Bun() })
    };

    public Recipe getRandomRecipe() {
        int index = (int)(Math.random() * ALL_RECIPES.length);
        return ALL_RECIPES[index];
    }
}
