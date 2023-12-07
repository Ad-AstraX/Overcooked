package Model.Holdables.Ingredients.Cookable;

import Model.Holdables.Ingredients.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Patty extends Ingredient implements Cookable{
    private boolean isCooked;
    public Patty(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    public void cook() {
        isCooked = true;
    }
    public boolean isCooked() {
        return isCooked;
    }

}
