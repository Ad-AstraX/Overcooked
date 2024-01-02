package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

/**
 * Abstract parent class for all kitchenCounters. Checks for and reacts to interaction checks
 */
public abstract class KitchenCounter extends WorldObject implements IInteractible {
    protected String[] textures;
    protected Player interactionPartner;
    protected boolean isInteracting;
    public KitchenCounter(String[] textures, Vector2 position, Vector2 size) {
        super(textures[0], position, size);
        this.textures = textures;
    }

    /**
     * Updates image depending on whether the object is being interacted with
     */
    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture(textures[1]);
        } else {
            this.setTexture(textures[0]);
        }
    }

    // All Getters
    public boolean isInteracting() {
        return isInteracting;
    }
    public Player getInteractionPartner() {
        return interactionPartner;
    }
    @Override
    public WorldObject getCopy() {
        return new Grill(this.position);
    }

    // All Setters
    public void setIsInteracting(boolean isInteracting) {
        this.isInteracting = isInteracting;
    }
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
    public void setTextures(String[] textures) {
        this.textures = textures;
    }
}
