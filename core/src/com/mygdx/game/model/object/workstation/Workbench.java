package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/** This class represents a regular workbench on which one can "store" an object / a plate */
public class Workbench extends KitchenCounter {
    /** The object that the workbench is currently storing */
    private IHoldable currentHoldable;
    public Workbench(Vector2 position) {
        super(new String[] {"Interactables/workbench.png", "Interactables/workbenchSelected.png"}, position, new Vector2(130, 160));
    }

    /** Method is called whenever player wishes to put on or remove a Holdable object from this Workbench */
    @Override
    public void interact() {
        // If the player wishes to drop IHoldable's on here
        if (interactionPartner.getHand() != null && currentHoldable == null) {
            playerDropItemOnWorkbench();
            return;
        }

        // If the player wishes to pick up IHoldable's from here
        if (interactionPartner.getHand() == null && currentHoldable != null) {
            playerPickupItemOnWorkbench();
            return;
        }

        // If the player wishes to place Ingredients onto the plate stored on the workbench
        if (interactionPartner.getHand() instanceof Plate && currentHoldable != null) {
            playerPlaceIngredientsOnWorkbenchPlate();
            return;
        }
    }

    private void playerDropItemOnWorkbench() {
        this.currentHoldable = interactionPartner.getHand();
        interactionPartner.setHand(null);
        ((WorldObject) currentHoldable).setPosition(positionObject((WorldObject) currentHoldable, this));

        if (currentHoldable instanceof Plate)
            updateIngredientPosition();
    }

    private void playerPickupItemOnWorkbench() {
        interactionPartner.setHand(this.currentHoldable);
        this.currentHoldable = null;
    }

    private void playerPlaceIngredientsOnWorkbenchPlate() {
        if (!(currentHoldable instanceof Plate))
            return;

        Stack<Ingredient> reversedPlayerStack = Utilities.invertStack(((Plate) currentHoldable).getIngredients());
        Stack<Ingredient> targetPlateStack = ((Plate) interactionPartner.getHand()).getIngredients();

        Plate combinedStacksPlate = new Plate(((Plate) currentHoldable).getPosition());

        while (!reversedPlayerStack.isEmpty()) {
            combinedStacksPlate.addIngredient(reversedPlayerStack.top());
            reversedPlayerStack.pop();
        }
        while (!targetPlateStack.isEmpty()) {
            combinedStacksPlate.addIngredient(targetPlateStack.top());
            targetPlateStack.pop();
        }

        interactionPartner.setHand(null);
        this.currentHoldable = combinedStacksPlate;
        updateIngredientPosition();
    }

    private void updateIngredientPosition() {
        Stack<Ingredient> stack = Utilities.copyStack(((Plate) currentHoldable).getIngredients());
        int placeInStack = Utilities.countStackElements(stack);
        while (!stack.isEmpty()) {
            stack.top().setPosition(new Vector2(this.position.x - stack.top().getSize().x/2 + this.size.x/2, ((Plate) currentHoldable).getPosition().y+10+10*placeInStack));
            placeInStack--;
            stack.pop();
        }
    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
}
