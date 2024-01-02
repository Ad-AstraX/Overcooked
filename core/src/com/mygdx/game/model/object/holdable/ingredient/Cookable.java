package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for all cookable classes / implementations
 */
public abstract class Cookable extends Ingredient {
    private double cookTime;
    private final String[] textures;
    public Cookable (String[] textures, Vector2 position, Vector2 size, double cookTime) {
        super(textures[0], position, size);
        this.cookTime = cookTime;
        this.textures = textures;
    }

    /**
     * Allows an uncooked cookable Object to be cooked
     * @param dt Time
     */
    public void cook(float dt) {
        cookTime -= dt;
        if (cookTime < 0) this.setTexture(textures[1]);
    }

    /**
     * Checks whether the object is already cooked
     * @return whether the object is cooked
     */
    public boolean isCooked() {
        return cookTime <= 0;
    }
}
