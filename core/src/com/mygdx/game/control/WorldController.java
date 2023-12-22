package com.mygdx.game.control;

import com.mygdx.game.model.World;

/**
 * Controls all world scenes
 */
public class WorldController {
    private World currentWorld = new World();
    public WorldController() {
        currentWorld.generateKitchenScene();
    }

    public World getCurrentWorld() {
        return currentWorld;
    }
}
