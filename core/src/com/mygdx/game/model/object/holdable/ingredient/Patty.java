package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents a Patty. If it is uncooked, then it can be put on the grill so that it is processed.
 * Once it is fully cooked, it can be stacked onto the plate and its texture is changed accordingly.
 * <p>The price of this Ingredient is five virtual coins</p>
 */
public class Patty extends Cookable {
    public Patty() {
        super(new String[] {"Ingredients/patty.png", "Ingredients/pattyCooked.png"}, Vector2.Zero, Vector2.Zero, 7);
        price = 5;
    }

    /** Constructor used by reflection in the IngredientSpawner class */
    public Patty(Vector2 position) {
        super(new String[] {"Ingredients/patty.png", "Ingredients/pattyCooked.png"}, position, new Vector2(75, 30), 7);
        price = 5;
    }
}
