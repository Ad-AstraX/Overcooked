package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * This class represents a regular workbench on which one can "store" an object / a plate
 */
public class Workbench extends KitchenCounter implements IInteractible {
    private IHoldable currentHoldable;
    public Workbench() {
        super("Interactables/workbench.png", Vector2.Zero, new Vector2(130, 160));
    }
    public Workbench(Vector2 position) {
        super("Interactables/workbench.png", position, new Vector2(130, 160));
    }

    /**
     * Method is called whenever player wishes to put or remove a Holdable object on this Workbench
     * <p>
     * @return Whether the Interaction was successful
     */
    @Override
    public boolean interact() {
        if (interactionPartner.getHand() != null && currentHoldable == null){
            this.currentHoldable = interactionPartner.getHand();
            currentHoldable.setInteractionPartner(null);
            ((WorldObject) interactionPartner.getHand()).setPosition(
                    new Vector2(this.position.x - ((WorldObject) interactionPartner.getHand()).getSize().x/2 + this.size.x/2, this.position.y)
            );
            interactionPartner.setHand(null);
            return true;
        } else if (interactionPartner.getHand() == null && currentHoldable != null) {
            interactionPartner.setHand(this.currentHoldable);
            currentHoldable.setInteractionPartner(interactionPartner);
            this.currentHoldable = null;
            return true;
        }
        return false;
    }

    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture("Interactables/workbenchSelected.png");
        } else {
            this.setTexture("Interactables/workbench.png");
        }
    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
    @Override
    public WorldObject getCopy() {
        return new Workbench(this.position);
    }
}
