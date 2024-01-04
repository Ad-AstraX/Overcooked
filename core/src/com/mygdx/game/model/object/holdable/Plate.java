package com.mygdx.game.model.object.holdable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.datastructures.Utilities;
import com.mygdx.game.model.object.holdable.ingredient.Bun;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;

/** Class which represents a plate. One can stack cut and cooked (if necessary) ingredients on it */
public class Plate extends WorldObject implements IHoldable {
    private final Stack<Ingredient> ingredients = new Stack<>();
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
            if (ingredients.top() instanceof Bun) {
                ingredients.top().setTexture("Ingredients/bunBottom.png");
                ingredients.top().setSize(new Vector2(ingredients.top().getTexture().getWidth(), ingredients.top().getTexture().getHeight()));
            }
            ingredients.push(ingredient);
        }
    }

    /** Removes the top ingredient on the plate (if there is any) */
    public void removeTop(){
        if (!ingredients.isEmpty()) {
            ingredients.top().getTexture().dispose();
            ingredients.pop();
            if (ingredients.top() instanceof Bun) {
                ingredients.top().setTexture("Ingredients/bunTop.png");
                ingredients.top().setSize(new Vector2(ingredients.top().getTexture().getWidth(), ingredients.top().getTexture().getHeight()));
            }
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
