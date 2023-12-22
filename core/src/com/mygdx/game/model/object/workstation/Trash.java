package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * This class represents a trash can, that can remove an object from the plate or the player's hand
 */
public class Trash extends KitchenCounter implements IInteractible {
    private IHoldable currentHoldable;
    public Trash(Vector2 position) {
        super("Interactables/trashCan.png", position, new Vector2(20, 20));
    }

    /**
     * Method is called whenever player wishes to dispose of a Holdable object
     * <p>
     * //@param holdable The Holdable object that is to be disposed of
     * @return Whether the Interaction was successful
     */
    @Override
    public boolean interact(/*IHoldable holdable*/) {
        return false;
    }

    @Override
    public void updateImage() {
        if (isInteracting) {
            this.setTexture("Interactables/trashCanSelected.png");
        } else {
            this.setTexture("Interactables/trashCan.png");
        }
    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
    @Override
    public WorldObject getCopy() {
        return new Trash(this.position);
    }
}
