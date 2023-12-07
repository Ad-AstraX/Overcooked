package Model.Other.Customer;

import Model.Holdables.Ingredients.Ingredient;
import Model.Holdables.Plate;
import Model.Other.Datastructures.Stack;

public class Recipe {
    private Stack<Ingredient> ingredients;
    private int recipeID;
    public Recipe(int recipeID) {
        this.recipeID = recipeID;
        // Insert hardcoded recipes
    }

    /**
     * This method takes a given plate and compares the stack of ingredients
     * on the plate to the stack of ingredients in this recipe
     * <p>
     * @param plate the plate that this recipe will be compared to
     * @return whether or not the recipe and given plate match
     */
    public boolean compareToPlate(Plate plate) {
        return false;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
