package Model.Interactables;

import Model.Holdables.Ingredients.Cookable.Cookable;
import Model.Holdables.Holdable;
import Model.WorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Grill extends WorldObject implements Interactable {
    private Cookable currentCookable;
    public Grill(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    /**
     * Method is called whenever player wishes to cook an uncooked Cookable object on this grill
     * <p>
     * @param holdable The Cookable object to be cooked by this grill
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(Holdable holdable) {
        if (holdable instanceof Cookable) {
            currentCookable = (Cookable) holdable;
        }
        return false;
    }

    public Cookable getCurrentCookable() {
        return currentCookable;
    }
}
