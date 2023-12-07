package Model.Interactables;

import Model.Holdables.Holdable;
import Model.WorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Trash extends WorldObject implements Interactable{
    private Holdable currentHoldable;
    public Trash(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    /**
     * Method is called whenever player wishes to dispose of a Holdable object
     * <p>
     * @param holdable The Holdable object that is to be disposed of
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
