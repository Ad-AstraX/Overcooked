package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * This class represents a regular workbench on which one can "store" an object / a plate
 */
public class Workbench extends KitchenCounter implements IInteractible {
    private IHoldable currentHoldable;
    public Workbench(Vector2 position) {
        super(new String[] {"Interactables/workbench.png", "Interactables/workbenchSelected.png"}, position, new Vector2(130, 160));
    }

    /** Method is called whenever player wishes to put or remove a Holdable object on this Workbench */
    @Override
    public void interact() {
        if (interactionPartner.getHand() != null && currentHoldable == null){
            this.currentHoldable = interactionPartner.getHand();
            interactionPartner.setHand(null);
            ((WorldObject) currentHoldable).setPosition(
                    new Vector2(this.position.x - ((WorldObject) currentHoldable).getSize().x/2 + this.size.x/2, this.position.y + this.size.y*0.6f)
            );
        } else if (interactionPartner.getHand() == null && currentHoldable != null) {
            interactionPartner.setHand(this.currentHoldable);
            this.currentHoldable = null;
        }
    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
}
