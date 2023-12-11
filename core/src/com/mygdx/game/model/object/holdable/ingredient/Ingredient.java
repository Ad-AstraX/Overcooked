package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

public abstract class Ingredient extends WorldObject implements IHoldable {
    public Ingredient(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }
}
