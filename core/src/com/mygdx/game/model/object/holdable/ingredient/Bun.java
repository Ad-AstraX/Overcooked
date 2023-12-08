package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

public class Bun extends Ingredient{
    public Bun(Vector2 position) {
        super("badlogic.png", position, new Vector2(20, 20));
    }

    @Override
    public boolean pickup() {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return "Bun";
    }
}