package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Class which represents Tomato
 */
public class Tomato extends Cuttable {
    public Tomato() {
        super(new String[] {"Ingredients/tomato.png", "Ingredients/tomatoSlices.png"}, Vector2.Zero, Vector2.Zero, 5);
        this.price = 2;
    }

    public Tomato(Vector2 position) {
        super(new String[] {"Ingredients/tomato.png", "Ingredients/tomatoSlices.png"}, position, new Vector2(60, 50), 5);
        this.price = 2;
    }
}
