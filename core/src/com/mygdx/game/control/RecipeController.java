package com.mygdx.game.control;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.customer.Recipe;
import com.mygdx.game.model.object.holdable.ingredient.*;

public class RecipeController {
    private static final Recipe[] ALL_RECIPES = {
            new Recipe(new Ingredient[] { new Bun(Vector2.Zero), new Patty(Vector2.Zero) }),
            new Recipe(new Ingredient[] { new Bun(Vector2.Zero), new Patty(Vector2.Zero), new Tomato(Vector2.Zero) }),
            new Recipe(new Ingredient[] { new Bun(Vector2.Zero), new Patty(Vector2.Zero), new Tomato(Vector2.Zero), new Salad(Vector2.Zero) })
    };

    public Recipe getRandomRecipe() {
        int index = (int)(Math.random() * ALL_RECIPES.length);
        return ALL_RECIPES[index];
    }
}
