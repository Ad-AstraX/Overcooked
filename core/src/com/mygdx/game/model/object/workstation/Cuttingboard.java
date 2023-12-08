package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICuttable;

public class Cuttingboard extends WorldObject implements IInteractible {
    private ICuttable currentCuttable;

    public Cuttingboard(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    /**
     * Method is called whenever player wishes to cut an uncut Cuttable object on this Cuttingboard
     * <p>
     * @param holdable The Cuttable object to be cut on this kitchen counter
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(IHoldable holdable) {
        return false;
    }

    public ICuttable getCurrentCuttable() {
        return currentCuttable;
    }
}
