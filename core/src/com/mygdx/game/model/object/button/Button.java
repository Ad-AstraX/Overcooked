package com.mygdx.game.model.object.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.view.Main;

/**
 * The abstract parent class for all Buttons. It can check if the mouse is hovering over the button and if the button
 * was clicked.
 */
public abstract class Button extends WorldObject {
    /** The textures that the buttons switch between when the mouse touches them/they are toggled */
    protected String[] textures;
    public Button(String[] textures, Vector2 position, Vector2 size) {
        super(textures[0], position, size);
        this.textures = textures;
    }

    /**
     * Checks if the mouse is within the bounds of this button
     * <p>
     * @return Whether the mouse is touching the button
     */
    protected boolean mouseOnButton () {
        return Main.getMousePosition().x > this.position.x && Main.getMousePosition().x < this.position.x + this.size.x &&
                Main.getMousePosition().y > this.position.y && Main.getMousePosition().y < this.position.y + this.size.y;
    }

    /**
     * Checks if the mouse was clicked while within the bounds of this button
     * <p>
     * @return whether the button was clicked
     */
    protected boolean mouseClicked() {
        return mouseOnButton() && Gdx.input.justTouched();
    }

    /**
     * Checks for interaction (by using the methods mouseOnButton() and mouseClicked())
     * and responds depending on the type of button (e.g. by turning music on and off)
     * <p>
     * @return whether the button was interacted with
     */
    public abstract boolean checkForInteraction();
}
