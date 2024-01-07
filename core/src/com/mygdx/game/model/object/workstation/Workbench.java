package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;
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
        // If the player wishes to put something here
        if (interactionPartner.getHand() != null && currentHoldable == null){
            // the object is taken from the player and positioned accordingly
            this.currentHoldable = interactionPartner.getHand();
            interactionPartner.setHand(null);
            ((WorldObject) currentHoldable).setPosition(positionObject((WorldObject) currentHoldable, this));
            // if currentHoldable is a plate, each of its ingredients' position must be matched to that of the plate
            if (currentHoldable instanceof Plate) {
                Stack<Ingredient> stack = Utilities.copyStack(((Plate) currentHoldable).getIngredients());
                int placeinStack = Utilities.countStackElements(stack);
                while (!stack.isEmpty()) {
                    stack.top().setPosition(new Vector2(this.position.x - stack.top().getSize().x/2 + this.size.x/2, ((Plate) currentHoldable).getPosition().y+10+10*placeinStack));
                    placeinStack--;
                    stack.pop();
                }
            }
        // If the player wishes to take the object that is stored here
        } else if (interactionPartner.getHand() == null && currentHoldable != null) {
            interactionPartner.setHand(this.currentHoldable);
            this.currentHoldable = null;
        // If the player wishes to stack all the elements on the plate that is stored here onto the plate that the player is currently holding
        } else if (interactionPartner.getHand() instanceof Plate && currentHoldable != null) {
            if (currentHoldable instanceof Plate) {
                Stack<Ingredient> copy = Utilities.invertStack(((Plate) currentHoldable).getIngredients());
                while (!copy.isEmpty()) {
                    ((Plate) interactionPartner.getHand()).addIngredient(copy.top());
                    copy.pop();
                }
                this.currentHoldable = null;
            // TODO this condition may never be needed. Need to look into this
            } else if (!(currentHoldable instanceof Cuttable) && !(currentHoldable instanceof Cookable)) {
                ((Plate) interactionPartner.getHand()).addIngredient((Ingredient) currentHoldable);
                this.currentHoldable = null;
            }
        }
    }

    // All Getters
    public IHoldable getCurrentHoldable() {
        return currentHoldable;
    }
}
