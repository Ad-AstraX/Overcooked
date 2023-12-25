package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.ingredient.*;
import com.mygdx.game.view.Main;

public class IngredientSpawner<IngredientType> extends KitchenCounter implements IInteractible {
    private final String[] textures;
    private final Class<IngredientType> ingredientType;

    public IngredientSpawner(Class<IngredientType> type, Vector2 position) {
        super("fallbackTexture.png", position, new Vector2(130, 160));
        textures = new String[] {
                "Interactables/Spawner/" + type.getSimpleName().toLowerCase() + "Spawner.png",
                "Interactables/Spawner/" + type.getSimpleName().toLowerCase() + "SpawnerSelected.png"
        };
        ingredientType = type;
        this.setTexture(textures[0]);
    }

    @Override
    public WorldObject getCopy() {
        return new IngredientSpawner<>(ingredientType, position);
    }

    /** Method is called whenever player wishes to spawn an Ingredient of type IngredientType from this IngredientSpawner */
    @Override
    public void interact() {
        if (this.interactionPartner.getHand() == null) {
            Ingredient holdable;
            try {
                holdable = (Ingredient) ingredientType.getDeclaredConstructor(Vector2.class).newInstance(interactionPartner.getPosition());
                this.interactionPartner.setHand(holdable);
                Main.getWorldObjectList().append(holdable);
            } catch (Exception ignored) {  }
        }
    }

    @Override
    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture(textures[1]);
        } else {
            this.setTexture(textures[0]);
        }
    }
}
