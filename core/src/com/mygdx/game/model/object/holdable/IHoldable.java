package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

/**
 * Interface for all Holdable classes / implementations
 */
public interface IHoldable {
    /**
     * Allows a player to pick up or put down a certain object
     * <p>
     * @param direction the direction of the player
     */
    void beCarriedByPlayer(Vector2 direction);
    Player getInteractionPartner();
    void setInteractionPartner(Player interactionPartner);
}
