package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

public class Floor extends WorldObject{
    public Floor(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }

    // TODO I need this but still figuring out what I can do with it
    @Override
    public WorldObject getCopy() {
        return null;
    }
}
