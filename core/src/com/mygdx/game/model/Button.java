package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.view.Main;

public class Button extends WorldObject {
    String[] textures;
    public Button(String[] textures, Vector2 position, Vector2 size) {
        super(textures[0], position, size);
        this.textures = textures;
    }

    public boolean mouseOnButton () {
        return Main.getMousePosition().x > this.position.x && Main.getMousePosition().x < this.position.x + this.size.x &&
                Main.getMousePosition().y > this.position.y && Main.getMousePosition().y < this.position.y + this.size.y;
    }

    public boolean mouseClicked() {
        if (mouseOnButton()) {
            this.setTexture(textures[1]);
            return Gdx.input.justTouched();
        } else this.setTexture(textures[0]);
        return false;
    }
}
