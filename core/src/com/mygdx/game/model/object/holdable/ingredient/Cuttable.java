package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * <p>The abstract parent class for all Cuttable instances.</p> <br>
 * Only instances of Cuttable can be put on the Cuttingboard. Once that happens, they can be cut.
 * Additionally, they can only be stacked onto a plate once they are fully cut.
 * Their texture changes accordingly.
 */
public abstract class Cuttable extends Ingredient {
    /** The time left until this object is fully cut */
    private double cutTime;
    /** The textures that this object switches between once it is cut */
    private final String[] textures;
    public Cuttable(String[] textures, Vector2 position, Vector2 size, double cutTime) {
        super(textures[0], position, size);
        this.cutTime = cutTime;
        this.textures = textures;
    }

    /**
     * Allows an uncut Cuttable Object to be cut
     * <p>
     * @param dt Time
     */
    public void cut(float dt) {
        cutTime -= dt;
        if (cutTime < 0) this.setTexture(textures[1]);
    }

    /**
     * Checks whether the object is already cut
     * <p>
     * @return whether the object is cut
     */
    public boolean isCut() {
        return cutTime < 0;
    }
}
