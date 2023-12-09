package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

public class Plate extends WorldObject implements IHoldable {
    private Stack<Ingredient> ingredients;
    public Plate(Vector2 position) {
        super("badlogic.png", position, new Vector2(20, 20));
    }

    /**
     * Adds an Ingredient to the stack of ingredients on the plate
     * <p>
     * @param ingredient the ingredient to be added to the stack
     */
    public void addIngredient(Ingredient ingredient){

    }

    @Override
    public boolean pickup() {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return "Plate";
    }

    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
