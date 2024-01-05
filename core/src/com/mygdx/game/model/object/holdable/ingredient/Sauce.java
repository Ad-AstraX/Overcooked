package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Class which represents sauce
 */
public class Sauce extends Ingredient{
    public Sauce() {
        super("Ingredients/sauce.png", Vector2.Zero, Vector2.Zero);
        price = 1;
    }

    public Sauce(Vector2 position) {
        super("Ingredients/sauce.png", position, new Vector2(80, 30));
        price = 1;
    }
}
