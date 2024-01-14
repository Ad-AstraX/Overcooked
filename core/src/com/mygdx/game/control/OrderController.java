package com.mygdx.game.control;

import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.customer.Customer;
import com.mygdx.game.model.object.customer.Order;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.model.utilities.Utilities;

/** Generates the orders for the customers  */
public class OrderController {
    /** The recipeController which controls the recipes for the orders */
    private final RecipeController recipeController;

    public OrderController(RecipeController recipes){
        recipeController = recipes;
    }

    /**
     * Generates a new Order which contains 1 recipe
     * <p>
     * @return the finished order
     */
    public Order generateNewOrder() {
        return new Order(recipeController.getRandomRecipe());
    }

    /**
     * This method takes a given plate and compares the stack of ingredients
     * on the plate to the stack of ingredients in this recipe
     * <p>
     * @param plate the plate that this recipe will be compared to
     * @return whether the recipe and given plate match
     */
    public static boolean compareCustomerOrderToPlate(Customer customer,  Plate plate) {
        if (plate == null || customer == null)
            return false;

        Stack<Ingredient> recipeStackCopy = Utilities.copyStack(customer.getOrder().getRecipe().getIngredients());
        Stack<Ingredient> plateStackCopy = Utilities.copyStack(plate.getIngredients());

        while (!recipeStackCopy.isEmpty() || !plateStackCopy.isEmpty()) {
            if (recipeStackCopy.top().getClass() != plateStackCopy.top().getClass())
                return false;

            recipeStackCopy.pop();
            plateStackCopy.pop();
        }

        return true;
    }
}
