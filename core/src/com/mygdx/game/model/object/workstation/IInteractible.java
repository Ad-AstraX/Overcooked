package com.mygdx.game.model.object.workstation;

import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * Interface for all Interactable classes / implementations
 */
public interface IInteractible {
    /**
     * A method that is implemented in every interactable realization.
     * This method is called whenever the player wishes to interact with a certain object.
     * The object then fulfills its task upon interaction
     * <p>
     * @return Whether the Interaction was successful
     */
    boolean interact();
}
