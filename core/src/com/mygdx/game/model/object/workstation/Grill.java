package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICookable;

/**
 * This class represents a Grill which can cook cookable objects
 */
public class Grill extends KitchenCounter implements IInteractible {
    private ICookable currentCookable;
    public Grill() {
        super("Interactables/grill.png", Vector2.Zero, new Vector2(130, 160));
    }
    public Grill(Vector2 position) {
        super("Interactables/grill.png", position, new Vector2(130, 160));
    }
    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture("Interactables/grillSelected.png");
        } else {
            this.setTexture("Interactables/grill.png");
        }
    }

    /**
     * Method is called whenever player wishes to cook an uncooked Cookable object on this grill
     * <p>
     * @param holdable The Cookable object to be cooked by this grill
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(IHoldable holdable) {
        return false;
    }

    public ICookable getCurrentCookable() {
        return currentCookable;
    }

    @Override
    public WorldObject getCopy() {
        return new Grill(this.position);
    }
}
