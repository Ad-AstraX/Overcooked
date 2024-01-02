package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.view.Main;

public abstract class Processable extends KitchenCounter {
    protected Ingredient currentIngredient;
    protected Rectangle progressBar;
    protected float timeTillFinish;

    public Processable(String[] textures, Vector2 position, Vector2 size, float timeTillFinish) {
        super(textures, position, size);
        this.timeTillFinish = timeTillFinish;
    }

    /**
     * Updates the current object on the kitchenCounter while it is being cooked or cut
     * @param dt Time
     */
    public void update(float dt) {
        if (currentIngredient != null ) {
            if (currentIngredient instanceof Cuttable && !((Cuttable) currentIngredient).isCut() ||
                currentIngredient instanceof Cookable && !((Cookable) currentIngredient).isCooked()) {
                try { ((Cuttable) currentIngredient).cut(dt); } catch (Exception ignore){}
                try { ((Cookable) currentIngredient).cook(dt); } catch (Exception ignore) {}
                progressBar.width -= dt*(100/timeTillFinish);
            } else if (progressBar != null && progressBar.width <= 0) {
                currentIngredient.setSize(new Vector2(currentIngredient.getTexture().getWidth(), currentIngredient.getTexture().getHeight()));
                currentIngredient.setPosition(
                        new Vector2(this.position.x - currentIngredient.getSize().x/2 + this.size.x/2, this.position.y + this.size.y*0.6f)
                );
                Main.getAllRectangles().toFirst();
                while (Main.getAllRectangles().hasAccess() && !Main.getAllRectangles().getContent().equals(progressBar)) {
                    Main.getAllRectangles().next();
                }
                Main.getAllRectangles().remove();
            }
        }
    }

    public void interact() {
        if (interactionPartner.getHand() != null && currentIngredient == null){
            this.currentIngredient = (Ingredient) interactionPartner.getHand();
            interactionPartner.setHand(null);
            currentIngredient.setPosition(
                    new Vector2(this.position.x - currentIngredient.getSize().x/2 + this.size.x/2, this.position.y + this.size.y*0.6f)
            );
            progressBar = new Rectangle(position.x+size.x/2-50, position.y+size.y/2, 100, 10);
            Main.getAllRectangles().append(progressBar);
        } else if (interactionPartner.getHand() == null) {
            interactionPartner.setHand(this.currentIngredient);
            this.currentIngredient = null;
        }
    }
}
