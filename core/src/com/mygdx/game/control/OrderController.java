package com.mygdx.game.control;

import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.customer.*;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.*;
import com.mygdx.game.model.utilities.Utilities;

/** Generates the orders for the customers  */
public class OrderController {
    Class[] ingredients = {Sauce.class, Patty.class, Lettuce.class, Tomato.class};
    /**
     * Generates a new Order which contains 1 recipe
     * <p>
     * @return the finished order
     */
    public Order generateNewOrder() {
        Stack<Ingredient> order = new Stack<>();
        order.push(new Bun());
        int length = 0;
        do {
            order.push(getRandomIngredient());
            length++;
        } while (!(Math.random() > 0.4) && length < 10);
        order.push(new Patty());
        do {
            order.push(getRandomIngredient());
            length++;
        } while (!(Math.random() > 0.4) && length < 10);
        order.push(new Bun());
        return new Order(order);
    }

    private Ingredient getRandomIngredient() {
        try {
            return (Ingredient) ingredients[(int) (Math.random() * ingredients.length)].getDeclaredConstructor().newInstance();
        } catch (Exception ignored) {}
        return null;
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

        Stack<Ingredient> recipeStackCopy = Utilities.copyStack(customer.getOrder().getRecipe());
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
