package com.mygdx.game.model.object.holdable.ingredient;

/**
 * Interface for all cookable classes / implementations
 */
public interface ICookable {
    /**
     * Allows an uncooked cookable Object to be cooked
     * @param dt Time
     */
    void cook(float dt);

    /**
     * Checks whether the object is already cooked
     * @return whether the object is cooked
     */
    boolean isCooked();
}
