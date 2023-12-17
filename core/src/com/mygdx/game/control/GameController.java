package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Game;
import com.mygdx.game.view.Main;

public class GameController {
    private final Game game;
    private final PlayerController playerController1;
    private final PlayerController playerController2;
    private final CustomerController customerController;
    private final OrderController orderController;
    private final WorldController worldController;

    public GameController(float roundLength, int payGoal, float customerSpawnChance) {
        game = new Game(roundLength, payGoal, customerSpawnChance);
        playerController1 = new PlayerController(
                new String[] {
                        "Players/PlayerOne/playerOrangeBehind.png",
                        "Players/PlayerOne/playerOrangeLeft.png",
                        "Players/PlayerOne/playerOrangeFront.png",
                        "Players/PlayerOne/playerOrangeRight.png"},
                new Vector2(400, 0),
                new int[]{Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D}
        );
        playerController2 = new PlayerController(
                new String[] {
                        "Players/PlayerTwo/playerGreenBehind.png",
                        "Players/PlayerTwo/playerGreenLeft.png",
                        "Players/PlayerTwo/playerGreenFront.png",
                        "Players/PlayerTwo/playerGreenRight.png"},
                new Vector2(600, 0),
                new int[]{Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT}
        );

        customerController = new CustomerController();
        orderController = new OrderController(new RecipeController());

        worldController = new WorldController();
        worldController.getCurrentWorld().getAllObjects().toFirst();
        while(worldController.getCurrentWorld().getAllObjects().hasAccess()) {
            Main.getWorldObjectList().append(worldController.getCurrentWorld().getAllObjects().getContent());
            worldController.getCurrentWorld().getAllObjects().next();
        }
    }

    public void mainLoop(float dt) {
        tickTime(dt);

        // TODO: Get correct input for players
        playerController1.UpdateInput(dt, false, false);
        playerController2.UpdateInput(dt, false, false);

        tickGenCustomer();
    }

    public void tickTime(float dt) {
        game.timeLeftLastFrame = game.timeLeft;
        game.timeLeft -= dt;
    }

    public void tickGenCustomer() {
        if (Math.floor(game.timeLeft) == Math.floor(game.timeLeftLastFrame))
            return;

        if (Math.random() * 100 < game.customerSpawnChance)
            return;

        System.out.println("Spawned Customer");
    }
}
