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
        if (!direction.equals(new Vector2(0, 1))) {
            if (getSize().x == 0) this.setSize(new Vector2(getTexture().getWidth(), getTexture().getHeight()));
            this.setPosition(new Vector2(
                    interactionPartner.getPosition().x - this.size.x / 2 + interactionPartner.getSize().x / 2 * (1 + direction.x),
                    interactionPartner.getPosition().y
            ));
        } else {
            this.setSize(new Vector2(0, 0));
        }
    }

    // All Getters
    public Player getInteractionPartner() {
        return interactionPartner;
    }
    @Override
    public WorldObject getCopy() {
        return new Sauce(this.position);
    }

    // All Setters
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
}
