package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is used for any backgroundObject, that is not really an instance of the other model classes and may just be for
 * decorative purposes
 */
public class BackgroundObject extends WorldObject{
    public BackgroundObject(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }
    public BackgroundObject(String texturePath, int cols, int rows, float frameDuration, Vector2 position, Vector2 size) {
        super(texturePath, cols, rows, frameDuration, position, size);
    }
}
