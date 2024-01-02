package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class for all cuttable objects / implementations
 */
public abstract class Cuttable extends Ingredient {
    private double cutTime;
    private final String[] textures;
    public Cuttable(String[] textures, Vector2 position, Vector2 size, double cutTime) {
        super(textures[0], position, size);
        this.cutTime = cutTime;
        this.textures = textures;
    }

    /**
     * Allows an uncuttable Cuttable Object to be cut
     * @param dt Time
     */
    public void cut(float dt) {
        cutTime -= dt;
        if (cutTime < 0) this.setTexture(textures[1]);
    }

    /**
     * Checks whether the object is already cut
     * @return whether the object is cut
     */
    public boolean isCut() {
        return cutTime < 0;
    }
}
