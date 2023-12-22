package com.mygdx.game.model.object.holdable;

import com.mygdx.game.model.Player;

/**
 * Interface for all Holdable classes / implementations
 */
public interface IHoldable {
    /**
     * Allows a player to pick up or put down a certain object
     * <p>
     * @param player the player that picks up or puts down
     * @return Whether the interaction was successful
     */
    boolean pickup(Player player);
    String getSubclassTypeName();
}
