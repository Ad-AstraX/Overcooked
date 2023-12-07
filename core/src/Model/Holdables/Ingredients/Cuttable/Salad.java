package Model.Holdables.Ingredients.Cuttable;

import Model.Holdables.Ingredients.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Salad extends Ingredient implements Cuttable{
    private boolean isCut;
    public Salad(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    @Override
    public void cut() {
        isCut = true;
    }

    public boolean isCut() {
        return isCut;
    }
}
