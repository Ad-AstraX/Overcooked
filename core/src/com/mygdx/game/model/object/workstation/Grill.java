package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICookable;

public class Grill extends WorldObject implements IInteractible {
    private ICookable currentCookable;

    public Grill(Vector2 position) {
        super("badlogic.jpg", position, new Vector2(20, 20));
    }

    /**
     * Method is called whenever player wishes to cook an uncooked Cookable object on this grill
     * <p>
     * @param holdable The Cookable object to be cooked by this grill
     * @return Whether or not the Interaction was successful
     */
    @Override
    public boolean interact(IHoldable holdable) {
        return false;
    }

    public ICookable getCurrentCookable() {
        return currentCookable;
    }
}
