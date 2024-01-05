package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * Abstract parent class for all ingredients
 */
public abstract class Ingredient extends WorldObject implements IHoldable {
    protected int price;
    protected Player interactionPartner;
    public Ingredient(String texturePath, Vector2 position, Vector2 size) {
        super(texturePath, position, size);
    }

    public void beCarriedByPlayer(Vector2 direction) {
        this.setPosition(new Vector2(
            interactionPartner.getPosition().x - this.size.x / 2 + interactionPartner.getSize().x / 2 * (1 + direction.x),
            interactionPartner.getPosition().y + 10
        ));
    }

    // All Getters
    public Player getInteractionPartner() {
        return interactionPartner;
    }
    public int getPrice() {
        return price;
    }

    // All Setters
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
