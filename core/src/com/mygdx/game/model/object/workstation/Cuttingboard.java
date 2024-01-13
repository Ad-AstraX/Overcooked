package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;

/**
 * This class represents a Cuttingboard. Only cuttable Objects can be placed on and processed here. The player cannot
 * remove an object that has been placed here until it is fully cut.
 */
public class Cuttingboard extends Processable {
    public Cuttingboard(Vector2 position) {
        super(new String[] {"Interactables/cuttingboard.png", "Interactables/cuttingboardSelected.png"},
                position, new Vector2(130, 160), 5, Gdx.audio.newSound(Gdx.files.internal("Sound/cuttingSound.mp3")));
    }

    /** Method is called whenever player wishes to cut an uncut Cuttable object on this Cuttingboard */
    @Override
    public void interact() {
        IHoldable playerHand = interactionPartner.getHand();
        /* Allows the player to only interact if they are either trying to place an uncut cuttable object here and the
           space is vacant OR if the player tries to take an object from here that has finished processing while having nothing
           in their hand or trying to stack the processed object onto a plate */
        if ((currentIngredient == null && playerHand instanceof Cuttable && !((Cuttable) playerHand).isCut()) ||
                ((playerHand == null || playerHand instanceof Plate) && currentIngredient != null && progressBar == null)){
            super.interact();
        }
    }
}
