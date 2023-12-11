package com.mygdx.game.model.object.customer;

import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.*;

public class Recipe {
    private Stack<Ingredient> ingredients;
    private int recipeID;
    public Recipe(int recipeID) {
        if (recipeID > 4) {
            recipeID = 4;
        }
        this.recipeID = recipeID;

        ingredients = new Stack<>();
        // Insert hardcoded recipes
        generateRecipe();
    }

    /**
     * This method takes a given plate and compares the stack of ingredients
     * on the plate to the stack of ingredients in this recipe
     * <p>
     * @param plate the plate that this recipe will be compared to
     * @return whether or not the recipe and given plate match
     */
    public boolean compareToPlate(Plate plate) {
        if (plate != null) {
            Stack<Ingredient> plateSaved = new Stack<>();
            boolean platesMatch = false;
            while(!ingredients.isEmpty() || !plate.getIngredients().isEmpty()) {
                if (ingredients.top() == plate.getIngredients().top()) {
                    ingredients.pop();
                    plateSaved.push(plate.getIngredients().top());
                    plate.getIngredients().pop();
                } else {
                    return false;
                }
            }
            if (ingredients.isEmpty() && plate.getIngredients().isEmpty()) {
                platesMatch = true;
            }
            while (!plateSaved.isEmpty()) {
                plate.getIngredients().push(plateSaved.top());
                plateSaved.pop();
            }
            return platesMatch;
        }
        return false;
    }

    /**
     * This method pushes a number of ingredients onto the ingredients stack
     */
    public void generateRecipe() {
        switch(recipeID) {
            //case zero: Bun Patty Bun
            case(0):
                ingredients.push(new Bun(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Bun(null));
                //case one: Bun Patty Salad Bun
            case(1):
                ingredients.push(new Bun(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Salad(null));
                ingredients.push(new Bun(null));
                //case two: Bun Patty Tomato Bun
            case(2):
                ingredients.push(new Bun(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Tomato(null));
                ingredients.push(new Bun(null));
                //case three: Bun Patty Tomato Salad Tomato Bun
            case(3):
                ingredients.push(new Bun(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Tomato(null));
                ingredients.push(new Salad(null));
                ingredients.push(new Tomato(null));
                ingredients.push(new Bun(null));
                //case four: Bun Salad Patty Tomato Patty Tomato Bun
            case(4):
                ingredients.push(new Bun(null));
                ingredients.push(new Salad(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Tomato(null));
                ingredients.push(new Patty(null));
                ingredients.push(new Tomato(null));
                ingredients.push(new Bun(null));
        }
    }

    public int getRecipeID() {
        return recipeID;
    }

    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
