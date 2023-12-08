package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class WorldObject {
    private final Texture texture;
    public Vector2 position;
    public Vector2 size;

    public WorldObject(String texturePath, Vector2 position, Vector2 size) {
        this.texture = new Texture(texturePath);
        this.position = position;
        this.size = size;
    }

    public Texture getTexture() {
        return texture;
    }
}
