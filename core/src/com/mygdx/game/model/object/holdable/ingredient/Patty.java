package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Class which represents a Meat patty
 */
public class Patty extends Cookable {
    public Patty() {
        super(new String[] {"Ingredients/patty.png", "Ingredients/pattyCooked.png"}, Vector2.Zero, Vector2.Zero, 7);
    }

    public Patty(Vector2 position) {
        super(new String[] {"Ingredients/patty.png", "Ingredients/pattyCooked.png"}, position, new Vector2(75, 50), 7);
    }
}
