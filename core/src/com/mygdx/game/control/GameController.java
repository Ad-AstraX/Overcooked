package com.mygdx.game.control;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Game;

public class GameController {
    private final Game game;
    private final PlayerController playerController1;
    private final PlayerController playerController2;
    private final CustomerController customerController;
    private final OrderController orderController;
    private final WorldController worldController;

    public GameController(float timeSeconds, int payGoal) {
        game = new Game(timeSeconds, payGoal);
        playerController1 = new PlayerController(new Vector2(2, 0));
        playerController2 = new PlayerController(new Vector2(-2, 0));
        customerController = new CustomerController(); // TODO
        orderController = new OrderController(); // TODO
        worldController = new WorldController(); // TODO
    }

    public void mainLoop(float dt) {
        tickTime(dt); // TODO: Get correct input
        playerController1.UpdateInput(dt, Vector2.Zero, false, false);
        playerController2.UpdateInput(dt, Vector2.Zero, false, false);
    }

    public void tickTime(float dt) {
        game.timeleft -= dt;
    }
}
