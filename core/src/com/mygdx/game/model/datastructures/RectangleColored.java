package com.mygdx.game.model.datastructures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class RectangleColored extends Rectangle {
    ShapeRenderer.ShapeType shapeType;
    private Color color;
    public RectangleColored(ShapeRenderer.ShapeType shapeType, float x, float y, int width, int height, float r, float g, float b, float a) {
        super(x, y, width, height);
        color = new Color(r, g, b, a);
        this.shapeType = shapeType;
    }
    public RectangleColored(ShapeRenderer.ShapeType shapeType, float x, float y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
        this.shapeType = shapeType;
    }

    public Color getColor() {
        return color;
    }
    public ShapeRenderer.ShapeType getShapeType() {
        return shapeType;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setColor(float r, float g, float b, float a) {
        if (a < 0) {
            a = 0;
        }
        this.color = new Color(r, g, b, a);
    }
    public void setAlpha(float a) {
        if (a < 0) a = 0;
        if (a > 1) a = 1;
        this.color.a = a;
    }
    public void setShapeType(ShapeRenderer.ShapeType shapeType) {
        this.shapeType = shapeType;
    }
}
