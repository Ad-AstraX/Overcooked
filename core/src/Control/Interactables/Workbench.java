package Control.Interactables;

import Control.Holdables.Holdable;
import Control.WorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Workbench extends WorldObject implements Interactable{
    private Holdable currentHoldable;
    public Workbench(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    /**
     * Method is called whenever player wishes to put or remove a Holdable object on this Workbench
     * <p>
     * @param holdable The ingredient or food to be stored on or removed from the kitchen counter
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(Holdable holdable) {
        return false;
    }
    public Holdable getCurrentHoldable() {
        return currentHoldable;
    }
}
