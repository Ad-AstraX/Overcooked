package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICuttable;

/**
 * This class represents a cuttingboard which can cut cuttable Objects
 */
public class Cuttingboard extends KitchenCounter implements IInteractible {
    private ICuttable currentCuttable;
    public Cuttingboard() {
        super("Interactables/cuttingboard.png", Vector2.Zero, new Vector2(130, 160));
    }

    public Cuttingboard(Vector2 position) {
        super("Interactables/cuttingboard.png", position, new Vector2(130, 160));
    }

    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture("Interactables/cuttingboardSelected.png");
        } else {
            this.setTexture("Interactables/cuttingboard.png");
        }
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

    @Override
    public WorldObject getCopy() {
        return new Cuttingboard(this.position);
    }
}
