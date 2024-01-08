package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents a Tomato. If it is uncut, then it can be put on the cuttingboard so that it is processed.
 * Once it is fully cut, it can be stacked onto the plate and its texture is changed accordingly.
 * <p>The price of this Ingredient is two virtual coins</p>
 */
public class Tomato extends Cuttable {
    public Tomato() {
        super(new String[] {"Ingredients/tomatoSlices.png"}, Vector2.Zero, new Vector2(80, 50), 5);
        this.price = 2;
    }

    /** Constructor used by reflection in the IngredientSpawner */
    public Tomato(Vector2 position) {
        super(new String[] {"Ingredients/tomato.png", "Ingredients/tomatoSlices.png"}, position, new Vector2(60, 50), 5);
        this.price = 2;
    }
}
