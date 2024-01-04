package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.datastructures.Utilities;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/**
 * This class represents a regular workbench on which one can "store" an object / a plate
 */
public class Workbench extends KitchenCounter implements IInteractible {
    private IHoldable currentHoldable;
    public Workbench(Vector2 position) {
        super(new String[] {"Interactables/workbench.png", "Interactables/workbenchSelected.png"}, position, new Vector2(130, 160));
    }

    /** Method is called whenever player wishes to put or remove a Holdable object on this Workbench */
    @Override
    public void interact() {
        if (interactionPartner.getHand() != null && currentHoldable == null){
            this.currentHoldable = interactionPartner.getHand();
            interactionPartner.setHand(null);
            ((WorldObject) currentHoldable).setPosition(positionObject((WorldObject) currentHoldable, this));
            if (currentHoldable instanceof Plate) {
                Stack<Ingredient> stack = Utilities.copyStack(((Plate) currentHoldable).getIngredients());
                int placeinStack = Utilities.countStackElements(stack);
                while (!stack.isEmpty()) {
                    stack.top().setPosition(new Vector2(this.position.x - stack.top().getSize().x/2 + this.size.x/2, ((Plate) currentHoldable).getPosition().y+10+10*placeinStack));
                    placeinStack--;
                    stack.pop();
                }
            }
        } else if (interactionPartner.getHand() == null && currentHoldable != null) {
            interactionPartner.setHand(this.currentHoldable);
            this.currentHoldable = null;
        } else if (interactionPartner.getHand() instanceof Plate && currentHoldable != null) {
            if (currentHoldable instanceof Plate) {
                Stack<Ingredient> copy = Utilities.invertStack(((Plate) currentHoldable).getIngredients());
                while (!copy.isEmpty()) {
                    ((Plate) interactionPartner.getHand()).addIngredient(copy.top());
                    copy.pop();
                }
                this.currentHoldable = null;
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
