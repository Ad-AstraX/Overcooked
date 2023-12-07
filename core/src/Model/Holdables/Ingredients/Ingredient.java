package Model.Holdables.Ingredients;

import Model.Holdables.Holdable;
import Model.WorldObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Type;

public abstract class Ingredient extends WorldObject implements Holdable {
    public Ingredient(Vector2 position, Vector2 size, Sprite texture) {
        super(position, size, texture);
    }

    public Type getSubclassType() {
        return this.getClass();
    }
}
