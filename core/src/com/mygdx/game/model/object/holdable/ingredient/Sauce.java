package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents Sauce. It can be stacked onto the plate as is.
 * <p>The price of this Ingredient is one virtual coins</p>
 */
public class Sauce extends Ingredient{
    public Sauce() {
        super("Ingredients/sauce.png", Vector2.Zero, new Vector2(80, 30));
        price = 1;
    }

    /** Constructor used by reflection in the IngredientSpawner class */
    public Sauce(Vector2 position) {
        super("Ingredients/sauce.png", position, new Vector2(80, 30));
        price = 1;
    }
}
