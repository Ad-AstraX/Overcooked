package com.mygdx.game.model.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class extends the regular Rectangle class that is given by libGdx. This one also takes parameters for Color
 * as well as the shapeType to allow more efficient rendering.
 */
public class RectangleColored extends Rectangle {
    /** Whether the rectangle is outlined or filled */
    ShapeRenderer.ShapeType shapeType;
    private final Color color;
    public RectangleColored(ShapeRenderer.ShapeType shapeType, float x, float y, int width, int height, float r, float g, float b, float a) {
        super(x, y, width, height);
        color = new Color(r, g, b, a);
        this.shapeType = shapeType;
    }

    // All Getters
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
    public Color getColor() {
        return color;
    }
    public ShapeRenderer.ShapeType getShapeType() {
        return shapeType;
    }

    // All Setters
    public void setAlpha(float a) {
        if (a < 0) a = 0;
        if (a > 1) a = 1;
        this.color.a = a;
    }
}
