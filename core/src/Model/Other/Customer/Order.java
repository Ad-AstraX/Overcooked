package Model.Other.Customer;

import Model.Holdables.Plate;

public class Order {
    private Recipe[] recipes;
    public Order(int recipeCount) {
        if (recipeCount > 3) {
            recipeCount = 3;
        }
    }

    /**
     * This method takes a Plate and searches through the hardcoded recipes to
     * find one that matches
     * <p>
     * @param plate The plate that is compared to the recipes
     * @return Whether or not a match between the given plate and a recipe was found
     */
    public boolean comparePlateToRecipes(Plate plate) {
        return false;
    }
}
