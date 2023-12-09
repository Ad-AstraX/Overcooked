package com.mygdx.game.model.object.holdable;

public interface IHoldable {

    /**
     * Allows Holdable objects to be picked up by the player
     * <p>
     * @return Whether or not the interaction was successful
     */
    public boolean pickup();

    /**
     * Gives info on the Type of a given Holdable instance
     * <p>
     * @return a String containing the type of the Holdable instance
     */
    public String getSubclassTypeName();
}
