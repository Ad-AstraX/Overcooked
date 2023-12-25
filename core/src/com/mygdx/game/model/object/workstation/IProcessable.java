package com.mygdx.game.model.object.workstation;

public interface IProcessable extends IInteractible{
    /**
     * Updates the current object on the kitchenCounter while it is being cooked or cut
     * @param dt Time
     */
    void update(float dt);
}
