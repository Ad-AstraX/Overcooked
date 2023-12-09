package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

public class Workbench extends WorldObject implements IInteractible {
    private IHoldable currentHoldable;
    public Workbench(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    /**
     * Method is called whenever player wishes to put or remove a Holdable object on this Workbench
     * <p>
     * @param holdable The ingredient or food to be stored on or removed from the kitchen counter
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
