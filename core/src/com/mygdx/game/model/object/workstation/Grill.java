package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICookable;
import com.mygdx.game.view.Main;

/**
 * This class represents a Grill which can cook cookable objects
 */
public class Grill extends KitchenCounter implements IProcessable {
    private ICookable currentCookable;
    private Rectangle progressBar;
    public Grill() {
        super("Interactables/grill.png", Vector2.Zero, new Vector2(130, 160));
    }
    public Grill(Vector2 position) {
        super("Interactables/grill.png", position, new Vector2(130, 160));
    }
    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture("Interactables/grillSelected.png");
        } else {
            this.setTexture("Interactables/grill.png");
        }
    }

    public void update(float dt) {
        if (currentCookable != null && !currentCookable.isCooked()) {
            currentCookable.cook(dt);
            progressBar.width -= dt*((float) 100 /7);
        } else if (currentCookable != null && currentCookable.isCooked() && progressBar != null) {
            Main.getAllRectangles().toFirst();
            while (Main.getAllRectangles().hasAccess() && !Main.getAllRectangles().getContent().equals(progressBar)) {
                Main.getAllRectangles().next();
            }
            Main.getAllRectangles().remove();
        }
    }

    /** Method is called whenever player wishes to cook an uncooked Cookable object on this grill */
    @Override
    public void interact() {
        if (interactionPartner.getHand() != null && currentCookable == null &&
                interactionPartner.getHand() instanceof ICookable && !((ICookable) interactionPartner.getHand()).isCooked()){
            this.currentCookable = (ICookable) interactionPartner.getHand();
            interactionPartner.setHand(null);
            ((WorldObject) currentCookable).setPosition(
                    new Vector2(this.position.x - ((WorldObject) currentCookable).getSize().x/2 + this.size.x/2, this.position.y-1)
            );
            progressBar = new Rectangle(position.x+size.x/2-50, position.y+size.y/2, 100, 10);
            Main.getAllRectangles().append(progressBar);
        } else if (interactionPartner.getHand() == null && currentCookable != null && currentCookable.isCooked()) {
            interactionPartner.setHand((IHoldable) this.currentCookable);
            this.currentCookable = null;
        }
    }

    // All Getters
    public ICookable getCurrentCookable() {
        return currentCookable;
    }
    @Override
    public WorldObject getCopy() {
        return new Grill(this.position);
    }
}
