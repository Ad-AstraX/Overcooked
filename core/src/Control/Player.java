package Control;

import Control.Holdables.Holdable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends WorldObject{
    public final float MOVEMENT_SPEED = 20;
    private Holdable hand;
    public Player(Vector2 position) {
        super(position, new Vector2(20, 20), new Sprite(new Texture("badlogic.jpg")));
    }

    public float getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }
    public Holdable getHand() {
        return hand;
    }
}
