package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/** This class represents a trash can, which can remove an object from the plate or the player's hand and delete it*/
public class Trash extends KitchenCounter {
    public Trash(Vector2 position) {
        super(new String[]{"Interactables/trashCan.png", "Interactables/trashCanSelected.png"}, position, new Vector2(130, 160));
    }

    /** Method is called whenever player wishes to dispose of a Holdable object in his hand. The money of the player is changed accordingly */
    @Override
    public void interact() {
        if (this.interactionPartner.getHand() != null) {
            if (this.interactionPartner.getHand() instanceof Plate) {
                GameController.getGame().setPayTotal(GameController.getGame().getPayTotal() - ((Plate) interactionPartner.getHand()).getIngredients().top().getPrice());
                ((Plate) interactionPartner.getHand()).removeTop();
            } else {
                GameController.getGame().setPayTotal(GameController.getGame().getPayTotal() - ((Ingredient) interactionPartner.getHand()).getPrice());
                ((WorldObject) this.interactionPartner.getHand()).getTexture().dispose();
                interactionPartner.setHand(null);
            }
        }
    }
}
