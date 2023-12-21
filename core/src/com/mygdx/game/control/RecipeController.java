package com.mygdx.game.control;

import com.mygdx.game.model.object.customer.Recipe;
import com.mygdx.game.model.object.holdable.ingredient.*;

/**
 * Controls all the recipes (all recipes are hardcoded here)
 */
public class RecipeController {
    private static final Recipe[] ALL_RECIPES = {
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Tomato(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Tomato(), new Lettuce(), new Bun() }),
            new Recipe( new Ingredient[] { new Bun(), new Patty(), new Sauce(), new Tomato(), new Lettuce(), new Bun() })
    };

    /**
     * Chooses a random recipe and returns it
     * <p>
     * @return a random recipe
     */
    public Recipe getRandomRecipe() {
        int index = (int)(Math.random() * ALL_RECIPES.length);
        return ALL_RECIPES[index];
    }
}
