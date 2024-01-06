package com.mygdx.game.model.object.button;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.WorldController;

public class GamemodeToggleButton extends Button {
    private boolean toggleOn = true;
    public GamemodeToggleButton(Vector2 position, Vector2 size) {
        super(new String[] {"Buttons/multiplayerButton.png", "Buttons/multiplayerButtonSelected.png", "Buttons/singleplayerButton.png", "Buttons/singleplayerButtonSelected.png"},
                position, size);
    }

    @Override
    public boolean checkForInteraction() {
        if (mouseOnButton()) {
            if (!this.texture.toString().equals("Textures/" + textures[1]) && toggleOn) this.setTexture(textures[1]);
            else if (!this.texture.toString().equals("Textures/" + textures[3]) && !toggleOn) this.setTexture(textures[3]);
            if (mouseClicked()) {
                toggleOn = !toggleOn;
                WorldController.setMultiplayerOn(toggleOn);
                return true;
            }
        } else {
            if (!this.texture.toString().equals("Textures/" + textures[0]) && toggleOn) this.setTexture(textures[0]);
            else if (!this.texture.toString().equals("Textures/" + textures[2]) && !toggleOn) this.setTexture(textures[2]);
        }
        return false;
    }
}
