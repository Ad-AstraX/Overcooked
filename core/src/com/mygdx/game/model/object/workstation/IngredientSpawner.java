package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.*;

/**
 * This class represents all IngredientSpawners in the scene. It can spawn different ingredients into the
 * hands of the player / onto the plate of the player
 * @param <IngredientType> The type of ingredient that is to be spawned here
 */
public class IngredientSpawner<IngredientType> extends KitchenCounter implements IInteractable {
    /** The type of ingredient that this class spawns */
    private final Class<IngredientType> ingredientType;
    /** The sound that is played when a new Sauce instance is created */
    private final Sound ketchupSound = Gdx.audio.newSound(Gdx.files.internal("Sound/ketchupSound.mp3"));

    public IngredientSpawner(Class<IngredientType> type, Vector2 position) {
        super(new String[]{"fallbackTexture.png", "fallbackTexture.png"}, position, new Vector2(130, 160));
        setTextures(new String[] {
                "Interactables/Spawner/" + type.getSimpleName().toLowerCase() + "Spawner.png",
                "Interactables/Spawner/" + type.getSimpleName().toLowerCase() + "SpawnerSelected.png"
        });
        this.setTexture(textures[0]);
        ingredientType = type;
    }

    /** Method is called whenever player wishes to spawn an Ingredient of type IngredientType from this IngredientSpawner */
    @Override
    public void interact() {
        // Only spawns an ingredient if the player's hand is empty, or they are trying to stack the ingredient onto a plate
        if (this.interactionPartner.getHand() == null || this.interactionPartner.getHand() instanceof Plate) {
            Ingredient holdable;
            try {
                // tries to instantiate an object of type ingredientType
                holdable = (Ingredient) ingredientType.getDeclaredConstructor(Vector2.class).newInstance(interactionPartner.getPosition());
                // If holdable is neither a bun nor sauce then the object is directly placed into the player's hand
                if (((holdable instanceof Cuttable && !((Cuttable) holdable).isCut()) ||
                    (holdable instanceof Cookable && !((Cookable) holdable).isCooked())) && this.interactionPartner.getHand() == null) {
                    this.interactionPartner.setHand(holdable);
                    pickUpSound.play(0.5f);
                // If holdable is either a bun or sauce then it must be placed onto a plate. If the player does not have a plate, one is created for him
                } else if (!(holdable instanceof Cookable) && !(holdable instanceof Cuttable)) {
                    if (this.interactionPartner.getHand() == null) this.interactionPartner.setHand(new Plate(interactionPartner.getPosition()));
                    ((Plate) this.interactionPartner.getHand()).addIngredient(holdable);

                    if (holdable instanceof Sauce) ketchupSound.play(0.5f);
                    else pickUpSound.play(0.5f);
                }
            } catch (Exception ignored) {  }
        }
    }
}
