package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * This class represents a trash can, that can remove an object from the plate or the player's hand
 */
public class Trash extends KitchenCounter implements IInteractible {
    private IHoldable currentHoldable;
    public Trash(Vector2 position) {
        super(new String[]{"Interactables/trashCan.png", "Interactables/trashCanSelected.png"}, position, new Vector2(20, 20));
    }

    /** Method is called whenever player wishes to dispose of a Holdable object */
    @Override
    public void interact() {

    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
}
