package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

public abstract class KitchenCounter extends WorldObject {
    protected Player interactionPartner;
    protected boolean isInteracting;
    public KitchenCounter(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }

    /**
     * Updates image depending on whether or not the object is being interacted with
     */
    public abstract void updateImage();

    public boolean isInteracting() {
        return isInteracting;
    }
    public void setIsInteracting(boolean isInteracting) {
        this.isInteracting = isInteracting;
    }
    public Player getInteractionPartner() {
        return interactionPartner;
    }
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
}
