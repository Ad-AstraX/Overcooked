package Control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class WorldObject {
    protected Sprite texture;
    protected Vector2 position;
    protected Vector2 size;

    public WorldObject(Vector2 position, Vector2 size, Sprite texture) {
        this.position = position;
        this.size = size;
        this.texture = texture;
    }

    // All Getters
    public Sprite getTexture() {
        return texture;
    }
    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getSize() {
        return size;
    }

    // All Setters
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void setSize(Vector2 size) {
        this.size = size;
    }
}
