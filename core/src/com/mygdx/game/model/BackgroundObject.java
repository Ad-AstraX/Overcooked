package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is used for the background
 */
public class BackgroundObject extends WorldObject{
    public BackgroundObject(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }
    public BackgroundObject(String texturePath, int cols, int rows, float frameDuration, Vector2 position) {
        super(texturePath, cols, rows, frameDuration, position);
    }

    // TODO I need this but still figuring out what I can do with it
}
