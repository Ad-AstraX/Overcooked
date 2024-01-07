package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.model.object.holdable.ingredient.Bun;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/**
 * Class which represents a plate. The player cannot choose when to instantiate it, rather one is created
 * wherever it is needed. One can stack ingredients on it as well as delete them by using the trash can.
 * If the ingredient that is to be stacked is an instance of Cuttable or Cookable, then it cannot be
 * stacked until it is cut or cooked.
 */
public class Plate extends WorldObject implements IHoldable {
    /** The stack of ingredients on the plate */
    private final Stack<Ingredient> ingredients = new Stack<>();
    /** The player that this plate is meant to follow around */
    private Player interactionPartner;

    public Plate(Vector2 position) {
        super("plate.png", position, new Vector2(100, 60));
    }

    /**
     * Adds an Ingredient to the stack of ingredients on the plate
     * <p>
     * @param ingredient the ingredient to be added to the stack
     */
    public void addIngredient(Ingredient ingredient){
        if (ingredient != null) {
            // Matches the texture of the bun if it had been the top element up until now
            if (ingredients.top() instanceof Bun) {
                ingredients.top().setTexture("Ingredients/bunBottom.png");
                ingredients.top().setSize(new Vector2(ingredients.top().getTexture().getWidth(), ingredients.top().getTexture().getHeight()));
            }
            ingredients.push(ingredient);
        }
    }

    /** Removes the top ingredient on the plate (if there is any). Is activated by using the trash can */
    public void removeTop(){
        if (!ingredients.isEmpty()) {
            ingredients.top().getTexture().dispose();
            ingredients.pop();
            // If the bun is the new top ingredient then its texture is changed
            if (ingredients.top() instanceof Bun) {
                ingredients.top().setTexture("Ingredients/bunTop.png");
                ingredients.top().setSize(new Vector2(ingredients.top().getTexture().getWidth(), ingredients.top().getTexture().getHeight()));
            }
            // If the plate is now empty then it is no longer needed and disposed of
            if (ingredients.isEmpty()) {
                this.getTexture().dispose();
                interactionPartner.setHand(null);
            }
        }
    }

    @Override
    public void beCarriedByPlayer(Vector2 direction) {
        this.setPosition(new Vector2(
                interactionPartner.getPosition().x - this.size.x/2 + interactionPartner.getSize().x/2 * (1 + direction.x),
                interactionPartner.getPosition().y + 10
        ));

        // Changes the position for each element in the ingredients stack
        Stack<Ingredient> copy = Utilities.copyStack(ingredients);
        int placeinStack = Utilities.countStackElements(copy);
        while (!copy.isEmpty()) {
            copy.top().setPosition(new Vector2(
                    this.position.x+this.size.x/2-copy.top().getSize().x/2,
                    this.position.y+10+10*placeinStack));
            placeinStack--;
            copy.pop();
        }
    }

    // All Getters
    public Stack<Ingredient> getIngredients() {
        return ingredients;
    }
    public Player getInteractionPartner() {
        return interactionPartner;
    }

    // All Setters
    public void setInteractionPartner(Player interactionPartner) {
        this.interactionPartner = interactionPartner;
    }
}
