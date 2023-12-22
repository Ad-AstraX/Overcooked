package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents sauce
 */
public class Sauce extends Ingredient{
    public Sauce() {
        super("Ingredients/sauce.png", Vector2.Zero, Vector2.Zero);
    }

    public Sauce(Vector2 position) {
        super("Ingredients/sauce.png", position, new Vector2(20, 20));
    }

    @Override
    public boolean pickup(Player player) {
        return false;
    }

    // All Getters
    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }
    @Override
    public WorldObject getCopy() {
        return new Sauce(this.position);
    }
}
