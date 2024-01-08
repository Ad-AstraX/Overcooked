package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;

/** The base class for every single object visible on the screen */
public abstract class WorldObject {
    /** The animation of this object (if it has any) */
    protected Animation<TextureRegion> animation = new Animation<>(1);
    /** The rows and columns used to seperate each frame in the texture */
    protected Vector2 colsAndRows = new Vector2(1, 1);
    /** Dictates whether the current Object is animated or simply static */
    protected boolean isAnimation;
    /** Stores the texture */
    protected Texture texture;
    /** Stores the position of this object */
    protected Vector2 position;
    /** Stores the size of this object */
    protected Vector2 size;


    public WorldObject(String texturePath, Vector2 position, Vector2 size) {
        try { this.texture = new Texture("Textures/" + texturePath); }
        catch(GdxRuntimeException e) { this.texture = new Texture("Textures/fallbackTexture.png"); }
        this.position = position;
        this.size = size;
    }

    public WorldObject(String texturePath, int cols, int rows, float frameDuration, Vector2 position, Vector2 size) {
        try { this.texture = new Texture("Textures/" + texturePath); }
        catch(GdxRuntimeException e) { this.texture = new Texture("Textures/fallbackTexture.png"); }

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/cols,texture.getHeight()/rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<>(frameDuration, frames);
        colsAndRows = new Vector2(cols, rows);
        isAnimation = true;

        this.position = position;
        this.size = size;
    }

    // All Getters
    public Texture getTexture() {
        return texture;
    }
    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getSize() {
        return size;
    }
    public boolean isAnimation() {
        return isAnimation;
    }
    public Animation<TextureRegion> getAnimation() {
        return animation;
    }
    public Vector2 getColsAndRows() {
        return colsAndRows;
    }

    // All Setters
    public void setTexture(String texture) {
        try {
            this.texture.dispose();
            this.texture = new Texture("Textures/" + texture);
        } catch(GdxRuntimeException e) {
            this.texture = new Texture("Textures/fallbackTexture.png");
        }
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void setSize(Vector2 size) {
        this.size = size;
    }
    public void setIsAnimation(boolean animation) {
        isAnimation = animation;
    }
    public void setAnimation(String texture, int cols, int rows, float frameDuration) {
        setTexture(texture);

        TextureRegion[][] tmp = TextureRegion.split(this.texture, this.texture.getWidth()/cols,this.texture.getHeight()/rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<>(frameDuration, frames);
        colsAndRows = new Vector2(cols, rows);
        isAnimation = true;

        this.size = new Vector2(animation.getKeyFrames()[0].getRegionWidth(), animation.getKeyFrames()[0].getRegionHeight());
    }
}
