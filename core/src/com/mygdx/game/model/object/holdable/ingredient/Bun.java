package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

public class Bun extends Ingredient{
    public Bun() {
        super("badlogic.jpg", Vector2.Zero, Vector2.Zero);
    }

    public Bun(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    @Override
    public boolean pickup(Player player) {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }

    @Override
    public WorldObject getCopy() {
        return new Bun(this.position);
    }
}
