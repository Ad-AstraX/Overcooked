package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Game;

/**
 * Controls the game - all other controllers are instantiated and used here
 */
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
                new Vector2(650, 500),
                new int[] { Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D, Input.Keys.E }
        );

        playerController2 = new PlayerController(
                new String[] {
                        "Players/PlayerTwo/playerGreenBehind.png",
                        "Players/PlayerTwo/playerGreenLeft.png",
                        "Players/PlayerTwo/playerGreenFront.png",
                        "Players/PlayerTwo/playerGreenRight.png"},
                new Vector2(650, 300),
                new int[] { Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT, Input.Keys.ENTER }
        );

        customerController = new CustomerController();
        orderController = new OrderController(new RecipeController());

        worldController = new WorldController();
    }

    /**
     * Updates the game state each second
     * <p>
     * @param dt time
     */
    public void mainLoop(float dt) {
        tickTime(dt);

        // TODO: Get correct input for players
        playerController1.updateInput(dt, false, false);
        playerController2.updateInput(dt, false, false);

        worldController.getCurrentWorld().getAllProcessableObjects().toFirst();
        while (worldController.getCurrentWorld().getAllProcessableObjects().hasAccess()) {
            worldController.getCurrentWorld().getAllProcessableObjects().getContent().update(dt);
            worldController.getCurrentWorld().getAllProcessableObjects().next();
        }

        tickGenCustomer();
    }

    /**
     * Calculates the time since and left since the last frame
     * <p>
     * @param dt Time
     */
    private void tickTime(float dt) {
        game.setTimeLeftLastFrame(game.getTimeLeft());
        game.setTimeLeft(game.getTimeLeft() - dt);
    }

    /**
     * Executes every second with a certain chance to generate a new customer
     */
    private void tickGenCustomer() {
        if (Math.floor(game.getTimeLeft()) == Math.floor(game.getTimeLeftLastFrame()))
            return;

        if (Math.random() * 100 < game.getCustomerSpawnChance())
            return;

        System.out.println("Spawned Customer");
    }
}
