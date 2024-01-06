package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;

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
    public KitchenCounter(String[] textures, int cols, int rows, float frameDuration, Vector2 position) {
        super(textures[0], cols, rows, frameDuration, position);
        this.textures = textures;
    }

    /**
     * Updates image depending on whether the object is being interacted with
     */
    public void updateImage() {
        if (this.isInteracting) {
            if (!isAnimation) this.setTexture(textures[1]);
            else this.setAnimation(this.textures[1], (int) colsAndRows.x, (int) colsAndRows.y, animation.getFrameDuration());
        } else {
            if (!isAnimation) this.setTexture(textures[0]);
            else this.setAnimation(this.textures[0], (int) colsAndRows.x, (int) colsAndRows.y, animation.getFrameDuration());
        }
    }


    /**
     * Position an object on a KitchenCounter
     * @param object the object that is to be positioned
     * @param relativeTo the kitchenCounter
     * @return the new Vector of the object
     */
    public Vector2 positionObject(WorldObject object, WorldObject relativeTo) {
       return  new Vector2(
                relativeTo.getPosition().x - object.getSize().x/2 + relativeTo.getSize().x/2,
                relativeTo.getPosition().y + relativeTo.getSize().y*0.6f + (object instanceof Cookable ? 20 : 0));
    }

    // All Getters
    public boolean isInteracting() {
        return isInteracting;
    }
    public Player getInteractionPartner() {
        return interactionPartner;
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
