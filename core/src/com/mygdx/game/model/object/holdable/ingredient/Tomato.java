package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

public class Tomato extends Ingredient implements ICuttable{
    private boolean isCut;

    public Tomato(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    @Override
    public boolean pickup() {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return "Tomato";
    }

    @Override
    public void cut() {
        isCut = true;
    }

    public boolean isCut() {
        return isCut;
    }
}