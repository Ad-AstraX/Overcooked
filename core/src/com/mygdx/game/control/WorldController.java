package com.mygdx.game.control;

import com.mygdx.game.model.World;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;

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
