package Model.Holdables;

import Model.Holdables.Ingredients.Ingredient;
import Model.Other.Datastructures.Stack;
import Model.WorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Type;

public class Plate extends WorldObject implements Holdable{
    private Stack<Ingredient> ingredients;
    public Plate(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
        ingredients = new Stack<>();
    }

    /**
     * Adds an Ingredient to the stack of ingredients on the plate
     * <p>
     * @param ingredient the ingredient to be added to the stack
     */
    public void addIngredient(Ingredient ingredient) {

    }

    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public Type getSubclassType() {
        return this.getClass();
    }
}
