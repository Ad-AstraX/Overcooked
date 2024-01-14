package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.GameController;
import com.mygdx.game.control.OrderController;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/** This class represents a cash register. Customers queue here so they can put their order. */
public class CashRegister extends KitchenCounter {
    private final Sound customerServed = Gdx.audio.newSound(Gdx.files.internal("Sound/serveCustomerSound.mp3"));

    public CashRegister(Vector2 position) {
        super(new String[]{"Interactables/cashRegister.png", "Interactables/cashRegisterSelected.png"}, 1, 6, 0.15f, position, new Vector2(132, 160));
    }

    @Override
    public void interact() {
        // Check if player is holding a plate
        IHoldable item = interactionPartner.getHand();
        if (!(item instanceof Plate))
            return;

        // Check if the contents of the plate are according to the customers order
        boolean success = OrderController.compareCustomerOrderToPlate(GameController.singleton.getCustomerController().getCustomerQ().front(), (Plate) item);
        if (!success)
            return;

        customerServed.play(1.0f);
        //customerServed.dispose();

        interactionPartner.setHand(null);
        GameController.singleton.getCustomerController().nextCustomer();
        GameController.getGame().setPayTotal(GameController.getGame().getPayTotal() + calculatePrice(((Plate) item).getIngredients()));
    }

    private int calculatePrice(Stack<Ingredient> burger) {
        int price = 0;
        while (!burger.isEmpty()) {
            price += burger.top().getPrice();
            burger.pop();
        }
        return price;
    }
}
