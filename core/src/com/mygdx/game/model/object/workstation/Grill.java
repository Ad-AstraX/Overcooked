package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;

/**
 * This class represents a Grill which can cook cookable objects
 */
public class Grill extends Processable {
    public Grill(Vector2 position) {
        super(new String[] {"Interactables/grill.png", "Interactables/grillSelected.png"}, position, new Vector2(130, 160), 7);
    }

    /** Method is called whenever player wishes to cook an uncooked Cookable object on this grill */
    @Override
    public void interact() {
        if ((interactionPartner.getHand() != null && currentIngredient == null &&
            interactionPartner.getHand() instanceof Cookable && !((Cookable) interactionPartner.getHand()).isCooked()) ||
            (interactionPartner.getHand() == null && currentIngredient != null && ((Cookable)currentIngredient).isCooked())){
            super.interact();
        }
    }
}
