package Control.Interactables;

import Control.Holdables.Ingredients.Cuttable.Cuttable;
import Control.Holdables.Holdable;
import Control.WorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Cuttingboard extends WorldObject implements Interactable{
    private Cuttable currentCuttable;
    public Cuttingboard(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    /**
     * Method is called whenever player wishes to cut an uncut Cuttable object on this Cuttingboard
     * <p>
     * @param holdable The Cuttable object to be cut on this kitchen counter
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(Holdable holdable) {
        if (holdable instanceof Cuttable) {
            currentCuttable = (Cuttable) holdable;
        }
        return false;
    }

    public Cuttable getCurrentCuttable() {
        return currentCuttable;
    }
}
