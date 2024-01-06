package com.mygdx.game.model.object.button;

import com.badlogic.gdx.math.Vector2;

public class InfoButton extends Button {
    public InfoButton(Vector2 position, Vector2 size) {
        super(new String[]{"Buttons/infoButton.png", "Buttons/infoButtonSelected.png"}, position, size);
    }

    public boolean checkForInteraction() {
        if (mouseOnButton()) {
            if (this.texture.toString().equals("Textures/" + textures[0])) this.setTexture(textures[1]);
            return mouseClicked();
        } else if (this.texture.toString().equals("Textures/" + textures[1])) this.setTexture(textures[0]);
        return false;
    }
}