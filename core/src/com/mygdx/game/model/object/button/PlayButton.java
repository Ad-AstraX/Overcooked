package com.mygdx.game.model.object.button;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.view.Main;

/**
 * This is the button that prompts the actual game to start and the kitchenScene to be created.
 * This is also instantiated once during the creation of the Game UI.
 */
public class PlayButton extends Button {
    public PlayButton(Vector2 position, Vector2 size) {
        super(new String[] {"Buttons/playButton.png", "Buttons/playButtonSelected.png"}, position, size);
    }

    public boolean checkForInteraction() {
        if (mouseOnButton()) {
            if (this.texture.toString().equals("Textures/" + textures[0])) this.setTexture(textures[1]);
            if (mouseClicked()) {
                Main.getGameController().getWorldController().setTransitionDarker(true);
                return true;
            }
        } else if (this.texture.toString().equals("Textures/" + textures[1])) this.setTexture(textures[0]);
        return false;
    }
}
