package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * <p>The abstract parent class for all Cookable instances.</p> <br>
 * Only instances of Cookable can be put on the grill. Once that happens, they can be cooked.
 * Additionally, they can only be stacked onto a plate once they are fully cooked.
 * Their texture changes accordingly.
 */
public abstract class Cookable extends Ingredient {
    /** The time left until this object is fully cooked */
    private double cookTime;
    /** The textures that this object switches between once it is cooked */
    private final String[] textures;
    public Cookable (String[] textures, Vector2 position, Vector2 size, double cookTime) {
        super(textures[0], position, size);
        this.cookTime = cookTime;
        this.textures = textures;
    }

    /**
     * Allows an uncooked cookable Object to be cooked
     * <p>
     * @param dt Time
     */
    public void cook(float dt) {
        cookTime -= dt;
        if (cookTime < 0) this.setTexture(textures[1]);
    }

    /**
     * Checks whether the object is already cooked
     * <p>
     * @return whether the object is cooked
     */
    public boolean isCooked() {
        return cookTime <= 0;
    }
}
