package com.mygdx.game.model.object.holdable.ingredient;

/**
 * Interface for all cuttable objects / implementations
 */
public interface ICuttable {
    /**
     * Allows an uncuttable Cuttable Object to be cut
     * @param dt Time
     */
    void cut(float dt);

    /**
     * Checks whether the object is already cut
     * @return whether the object is cut
     */
    boolean isCut();
}
