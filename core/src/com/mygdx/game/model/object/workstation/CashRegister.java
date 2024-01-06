package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;

public class CashRegister extends KitchenCounter {

    public CashRegister(Vector2 position) {
        super(new String[]{"Interactables/cashRegister.png", "Interactables/cashRegisterSelected.png"}, 1, 6, 0.15f, position);
    }

    @Override
    public void interact() {
        // TODO complete cash register interaction
    }
}
