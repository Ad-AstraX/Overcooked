package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class WorldObject {
    private Texture texture;
    public Vector2 position;
    public Vector2 size;

    public WorldObject(String texturePath, Vector2 position, Vector2 size) {
        try {
            this.texture = new Texture(texturePath);
        } catch(GdxRuntimeException e) {
            this.texture = new Texture("fallbackTexture.png");
        }
        this.position = position;
        this.size = size;
    }

    // All Getters
    public Texture getTexture() {
        return texture;
    }
    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getSize() {
        return size;
    }

    // All Setters
    public void setTexture(String texture) {
        try {
            this.texture = new Texture(texture);
        } catch(GdxRuntimeException e) {
            this.texture = new Texture("fallbackTexture.png");
        }
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void setSize(Vector2 size) {
        this.size = size;
    }
}
