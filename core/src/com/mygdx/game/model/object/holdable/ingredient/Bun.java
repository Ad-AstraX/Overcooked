package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * This class represents a burger bun (top and bottom)
 */
public class Bun extends Ingredient{
    public Bun() {
        super("Ingredients/bunTop.png", Vector2.Zero, Vector2.Zero);
    }

    public Bun(Vector2 position) {
        super("Ingredients/bunTop.png", position, new Vector2(80, 150));
    }

    // All Getters
    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }
    @Override
    public WorldObject getCopy() {
        return new Bun(this.position);
    }
}
