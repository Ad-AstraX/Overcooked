package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

public class Trash extends WorldObject implements IInteractible {
    private IHoldable currentHoldable;
    public Trash(Vector2 position) {
        super("Interactables/trashCan.png", position, new Vector2(20, 20));
    }

    /**
     * Method is called whenever player wishes to dispose of a Holdable object
     * <p>
     * @param holdable The Holdable object that is to be disposed of
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(IHoldable holdable) {
        return false;
    }

    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
}
