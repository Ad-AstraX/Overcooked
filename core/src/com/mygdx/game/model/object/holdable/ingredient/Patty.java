package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

public class Patty extends Ingredient implements ICookable {
    private boolean isCooked;
    public Patty(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    @Override
    public void cook() {
        isCooked = true;
    }

    @Override
    public boolean pickup() {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return "Patty";
    }

    public boolean isCooked() {
        return isCooked;
    }
}
