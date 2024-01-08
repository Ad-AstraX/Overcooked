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

    /**
     * This method takes a given plate and compares the stack of ingredients
     * on the plate to the stack of ingredients in this recipe
     * <p>
     * @param plate the plate that this recipe will be compared to
     * @return whether the recipe and given plate match
     */
    public boolean compareToPlate(Plate plate) {
        if (plate == null)
            return false;

        Stack<Ingredient> recipeStackCopy = Utilities.copyStack(this.getIngredients());
        Stack<Ingredient> plateStackCopy = Utilities.copyStack(plate.getIngredients());

        while (!recipeStackCopy.isEmpty() || !plateStackCopy.isEmpty()) {
            if (recipeStackCopy.top().getClass() != plateStackCopy.top().getClass())
                return false;

            recipeStackCopy.pop();
            plateStackCopy.pop();
        }

        return true;
    }

    // All Getters
    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
