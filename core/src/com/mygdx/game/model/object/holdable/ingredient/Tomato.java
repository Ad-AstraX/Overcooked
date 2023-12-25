package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents Tomato
 */
public class Tomato extends Ingredient implements ICuttable{
    private double cutTime;

    public Tomato() {
        super("Ingredients/tomato.png", Vector2.Zero, Vector2.Zero);
    }

    public Tomato(Vector2 position) {
        super("Ingredients/tomato.png", position, new Vector2(60, 150));
    }

    @Override
    public void cut(float dt) {
        cutTime += dt;
        if (cutTime > 5) {
            cutTime = 5;
            this.setTexture("Ingredients/tomatoSlices.png");
            this.size.x = 75;
            this.position.x -= 10;
        }
    }

    // All Getters
    public boolean isCut() {
        return cutTime == 5;
    }
    @Override
    public WorldObject getCopy() {
        return new Tomato(this.position);
    }
}
