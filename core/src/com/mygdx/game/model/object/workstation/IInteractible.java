package com.mygdx.game.model.object.workstation;

/**
 * Interface for all Interactable classes / implementations. Classes which implement this interface allow
 * interaction with the player. The player they interact with is stored in the attribute "interactionPartner"
 */
public interface IInteractible {
    /**
     * This method is called whenever the player wishes to interact with a certain object.
     * The object then fulfills its task upon interaction
     */
    void interact();
}
