package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;

/** This class represents a cash register. Customers queue here so they can put their order. */
public class CashRegister extends KitchenCounter {

    public CashRegister(Vector2 position) {
        super(new String[]{"Interactables/cashRegister.png", "Interactables/cashRegisterSelected.png"}, 1, 6, 0.15f, position, new Vector2(132, 160));
    }

    @Override
    public void interact() {
        // TODO complete cash register interaction
    }
}
