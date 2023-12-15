package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

public class Patty extends Ingredient implements ICookable {
    private boolean isCooked;
    public Patty() {
        super("badlogic.jpg", Vector2.Zero, Vector2.Zero);
    }

    public Patty(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    @Override
    public void cook() {
        isCooked = true;
    }

    @Override
    public boolean pickup(Player player) {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }

    public boolean isCooked() {
        return isCooked;
    }
}
