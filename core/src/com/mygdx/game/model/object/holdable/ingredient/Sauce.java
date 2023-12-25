package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents sauce
 */
public class Sauce extends Ingredient{
    public Sauce() {
        super("Ingredients/sauce.png", Vector2.Zero, Vector2.Zero);
    }

    public Sauce(Vector2 position) {
        super("Ingredients/sauce.png", position, new Vector2(80, 150));
    }

    // All Getters
    @Override
    public WorldObject getCopy() {
        return new Sauce(this.position);
    }
}
