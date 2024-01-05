package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents a burger bun (top and bottom)
 */
public class Bun extends Ingredient{
    public Bun() {
        super("Ingredients/bunTop.png", Vector2.Zero, Vector2.Zero);
        price = 3;
    }

    public Bun(Vector2 position) {
        super("Ingredients/bunTop.png", position, new Vector2(80, 60));
        price = 3;
    }
}
