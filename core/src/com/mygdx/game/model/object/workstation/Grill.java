package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;

/**
 * This class represents a Grill. Only uncooked Objects can be placed on and processed here. The player cannot
 * remove an object that has been placed here until it is fully cooked.
 */
public class Grill extends Processable {
    public Grill(Vector2 position) {
        super(new String[]{"Interactables/grill.png", "Interactables/grillSelected.png", "Interactables/grillActivated.png", "Interactables/grillActivatedSelected.png"},
                position, new Vector2(130, 160), 7, Gdx.audio.newSound(Gdx.files.internal("Sound/grillingSound.mp3")));
    }

    @Override
    public void updateImage() {
        // updateImage() is a bit different here because the grill can turn on and off
        if (!this.isInteracting && progressBar == null) this.setTexture(textures[0]);
        else if (!this.isInteracting) this.setTexture(textures[2]);
        else if (progressBar == null) this.setTexture(textures[1]);
        else this.setTexture(textures[3]);
    }

    /** Method is called whenever player wishes to cook an uncooked Cookable object on this grill */
    @Override
    public void interact() {
        IHoldable playerHand = interactionPartner.getHand();
        /* Allows the player to only interact if they are either trying to place an uncooked cookable object here and the
           space is vacant OR if the player tries to take an object from here that has finished processing while having nothing
           in their hand or trying to stack the processed object onto a plate */
        if ((currentIngredient == null && playerHand instanceof Cookable && !((Cookable) playerHand).isCooked()) ||
            ((playerHand == null || playerHand instanceof Plate) && currentIngredient != null && progressBar == null)){
            super.interact();
        }
    }
}
