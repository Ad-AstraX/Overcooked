package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents a burger bun (top and bottom). It can be stacked onto a plate and changes
 * its texture depending on whether it is the top element in the stack of ingredients on the plate.
 * <p>The price of this bun is three virtual coins </p>
 */
public class Bun extends Ingredient{
    public Bun() {
        super("Ingredients/bunTop.png", Vector2.Zero, Vector2.Zero);
        price = 3;
    }

    /** Constructor used by reflection in the IngredientSpawner class */
    public Bun(Vector2 position) {
        super("Ingredients/bunTop.png", position, new Vector2(80, 60));
        price = 3;
    }
}
