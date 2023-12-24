package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * Abstract parent class for all ingredients
 */
public abstract class Ingredient extends WorldObject implements IHoldable {
    protected Player interactionPartner;
    public Ingredient(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }

    public void beCarriedByPlayer(Vector2 direction) {
        // TODO PLAYER POSITION GETS CHANGED HERE I DON'T KNOW THE FUCK WHY
        Vector2 lastPlayerPos = interactionPartner.getPosition().cpy();

        if (!direction.equals(new Vector2(0, 1))) {
            position.y = interactionPartner.getPosition().y - 90;
        } else {
            position.y = interactionPartner.getPosition().y + 1;
        }

        if (direction.equals(new Vector2(0, -1)) || direction.equals(new Vector2(0, 1))) {
            position.x = interactionPartner.getPosition().x - this.size.x/2 + interactionPartner.getSize().x/2;
        } else if (direction.equals(new Vector2(-1, 0))) {
            position.x = interactionPartner.getPosition().x - this.size.x/3;
        } else if (direction.equals(new Vector2(1, 0))) {
            position.x = interactionPartner.getPosition().x + interactionPartner.getSize().x - this.size.x/2;
        }

        interactionPartner.setPosition(lastPlayerPos);
    }

    // All Getters
    public Player getInteractionPartner() {
        return interactionPartner;
    }

    // All Setters
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
}
