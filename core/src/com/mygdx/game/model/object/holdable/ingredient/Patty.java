package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents a Meat patty
 */
public class Patty extends Ingredient implements ICookable {
    private double cookTime;
    public Patty() {
        super("Ingredients/patty.png", Vector2.Zero, Vector2.Zero);
    }

    public Patty(Vector2 position) {
        super("Ingredients/patty.png", position, new Vector2(75, 150));
    }

    @Override
    public void cook(float dt) {
        cookTime += dt;
        if (cookTime > 7) {
            cookTime = 7;
            this.setTexture("Ingredients/pattyCooked.png");
        }
    }

    // All Getters
    public boolean isCooked() {
        return cookTime == 7;
    }
    @Override
    public WorldObject getCopy() {
        return new Patty(this.position);
    }
}
