package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

/**
 * Interface for all Holdable classes / implementations. Classes which implement this interface can
 * be carried and put down by the player. The player they follow is stored in their attribute "interactionPartner"
 */
public interface IHoldable {
    /**
     * Allows a player to carry a certain object. When this method is called, the object changes its
     * position to match that of the player as well as the direction the player is currently facing.
     * <p>
     * @param direction the direction of the player
     */
    void beCarriedByPlayer(Vector2 direction);

    Player getInteractionPartner();
    void setInteractionPartner(Player interactionPartner);
}
