package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

public class Plate extends WorldObject implements IHoldable {
    private Stack<Ingredient> ingredients;
    private boolean pickedUp;
    public Plate(Vector2 position) {
        super("badlogic.png", position, new Vector2(20, 20));
    }

    /**
     * Adds an Ingredient to the stack of ingredients on the plate
     * <p>
     * @param ingredient the ingredient to be added to the stack
     */
    public void addIngredient(Ingredient ingredient){
        if (ingredient != null) {
            ingredients.push(ingredient);
        }
    }

    @Override
    public boolean pickup(Player player) {
        if (player != null){
            pickedUp = true;
            position.set(new Vector2(player.getPosition()).add(new Vector2(5, 5)));
            return true;
        }
        return false;
    }

    @Override
    public boolean putDown(Player player) {
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return "Plate";
    }

    public boolean getPickedUp() {
        return pickedUp;
    }

    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
}
