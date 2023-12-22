package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents Tomato
 */
public class Tomato extends Ingredient implements ICuttable{
    private boolean isCut;

    public Tomato() {
        super("Ingredients/tomato.png", Vector2.Zero, Vector2.Zero);
    }

    public Tomato(Vector2 position) {
        super("Ingredients/tomato.png", position, new Vector2(50, 55));
    }

    @Override
    public boolean pickup(Player player) {
        return false;
    }
    @Override
    public void cut() {
        isCut = true;
    }

    // All Getters
    public boolean isCut() {
        return isCut;
    }
    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }
    @Override
    public WorldObject getCopy() {
        return new Tomato(this.position);
    }
}
