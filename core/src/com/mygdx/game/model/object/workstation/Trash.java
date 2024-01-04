package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.Plate;

/** This class represents a trash can, that can remove an object from the plate or the player's hand */
public class Trash extends KitchenCounter {
    public Trash(Vector2 position) {
        super(new String[]{"Interactables/trashCan.png", "Interactables/trashCanSelected.png"}, position, new Vector2(100, 155));
    }

    /** Method is called whenever player wishes to dispose of a Holdable object */
    @Override
    public void interact() {
        if (this.interactionPartner.getHand() != null) {
            if (this.interactionPartner.getHand() instanceof Plate) {
                ((Plate) interactionPartner.getHand()).removeTop();
            } else {
                ((WorldObject) this.interactionPartner.getHand()).getTexture().dispose();
                interactionPartner.setHand(null);
            }
            GameController.getGame().setPayTotal(GameController.getGame().getPayTotal() - 10);
        }
    }
}
