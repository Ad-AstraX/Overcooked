package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.*;

public class IngredientSpawner<IngredientType> extends KitchenCounter implements IInteractible {
    private String[] textures = new String[] {"fallbackTexture.png"};

    public IngredientSpawner(Class<IngredientType> type, Vector2 position) {
        super("fallbackTexture.png", position, new Vector2(130, 160));
        if (type.equals(Tomato.class)) {
            textures = new String[] {"Interactables/Spawner/tomatoSpawner.png", "Interactables/Spawner/tomatoSpawnerSelected.png"};
        } else if (type.equals(Lettuce.class)) {
            textures = new String[] {"Interactables/Spawner/lettuceSpawner.png", "Interactables/Spawner/lettuceSpawnerSelected.png"};
        } else if (type.equals(Patty.class)) {
            textures = new String[] {"Interactables/Spawner/pattySpawner.png", "Interactables/Spawner/pattySpawnerSelected.png"};
        } else if (type.equals(Sauce.class)) {
            textures = new String[] {"Interactables/Spawner/sauceSpawner.png", "Interactables/Spawner/sauceSpawnerSelected.png"};
        }  else if (type.equals(Bun.class)) {
            textures = new String[] {"Interactables/Spawner/bunSpawner.png", "Interactables/Spawner/bunSpawnerSelected.png"};
        }
        this.setTexture(textures[0]);
    }

    @Override
    public WorldObject getCopy() {
        return null;
    }

    @Override
    public boolean interact(IHoldable holdable) {
        return false;
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
