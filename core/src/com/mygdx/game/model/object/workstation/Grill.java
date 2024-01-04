package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;

/**
 * This class represents a Grill which can cook cookable objects
 */
public class Grill extends Processable {
    public Grill(Vector2 position) {
        super(new String[]{"Interactables/grill.png", "Interactables/grillSelected.png", "Interactables/grillActivated.png", "Interactables/grillActivatedSelected.png"},
                position, new Vector2(130, 160), 7);
    }

    @Override
    public void updateImage() {
        if (!this.isInteracting && progressBar == null) this.setTexture(textures[0]);
        else if (!this.isInteracting) this.setTexture(textures[2]);
        else if (progressBar == null) this.setTexture(textures[1]);
        else this.setTexture(textures[3]);
    }

    /** Method is called whenever player wishes to cook an uncooked Cookable object on this grill */
    @Override
    public void interact() {
        IHoldable playerHand = interactionPartner.getHand();
        if ((currentIngredient == null && playerHand instanceof Cookable && !((Cookable) playerHand).isCooked()) ||
            ((playerHand == null || playerHand instanceof Plate) && currentIngredient != null && progressBar == null)){
            super.interact();
        }
    }
}
