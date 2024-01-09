package com.mygdx.game.model.object.customer;

import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.*;

/**
 * <p>This class represents a recipe. </p> <br>
 * It contains a stack of ingredients and can compare that stack to a given plate. The recipe is contained
 * within an order that the customer places. All recipes are hardcoded.
 */
public class Recipe {
    /** The ingredients that this recipe consists of */
    private final Stack<Ingredient> ingredients;

    public Recipe(Ingredient[] ingredientsBottomToTop) {
        ingredients = new Stack<>();
        for (Ingredient ingredient : ingredientsBottomToTop)
            ingredients.push(ingredient);
    }

    // All Getters
    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
