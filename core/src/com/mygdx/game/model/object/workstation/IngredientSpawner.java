package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.*;

public class IngredientSpawner<IngredientType> extends KitchenCounter implements IInteractible {
    private final Class<IngredientType> ingredientType;

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
        if (this.interactionPartner.getHand() == null || this.interactionPartner.getHand() instanceof Plate) {
            Ingredient holdable;
            try {
                holdable = (Ingredient) ingredientType.getDeclaredConstructor(Vector2.class).newInstance(interactionPartner.getPosition());
                if (((holdable instanceof Cuttable && !((Cuttable) holdable).isCut()) ||
                    (holdable instanceof Cookable && !((Cookable) holdable).isCooked())) && this.interactionPartner.getHand() == null) {
                    this.interactionPartner.setHand(holdable);
                } else if (!(holdable instanceof Cookable) && !(holdable instanceof Cuttable)) {
                    if (this.interactionPartner.getHand() == null) this.interactionPartner.setHand(new Plate(interactionPartner.getPosition()));
                    ((Plate) this.interactionPartner.getHand()).addIngredient(holdable);
                }
            } catch (Exception ignored) {  }
        }
    }
}
