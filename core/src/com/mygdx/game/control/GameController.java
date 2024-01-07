package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Game;
import com.mygdx.game.view.Main;

/**
 * Controls the game - all other controllers are instantiated and used here
 */
public class GameController {
    private static Game game;
    private final PlayerController playerController1;
    private PlayerController playerController2 = null;
    private final CustomerController customerController;
    private final OrderController orderController;
    private final WorldController worldController;

    public GameController(float roundLength, int payGoal, float customerSpawnChance) {
        game = new Game(roundLength, payGoal, customerSpawnChance);

        playerController1 = new PlayerController(
                new String[] {
                        "Players/PlayerTwo/playerGreenBehind.png",
                        "Players/PlayerTwo/playerGreenLeft.png",
                        "Players/PlayerTwo/playerGreenFront.png",
                        "Players/PlayerTwo/playerGreenRight.png"},
                new Vector2(750, 300),
                new int[] { Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT, Input.Keys.ENTER }
        );
        Main.getPlayers()[0] = playerController1.getPlayer();

        customerController = new CustomerController();
        orderController = new OrderController(new RecipeController());

        worldController = new WorldController();
        worldController.showStart();
    }

    /**
     * Updates the game state each second
     * <p>
     * @param dt time
     */
    public void mainLoop(float dt) {
        tickTime(dt);

        if (worldController.getSceneID() == 1) {
            playerController1.updateInput(dt);
            if (WorldController.isMultiplayerOn()) playerController2.updateInput(dt);
        }

        worldController.update(dt);

        tickGenCustomer();
    }

    /**
     * // TODO NEED TO LOOK INTO THAT
     * <p>
     * @param dt Time
     */
    private void tickTime(float dt) {
        game.setTimeLeftLastFrame(game.getTimeLeft());
        game.setTimeLeft(game.getTimeLeft() - dt);
    }

    /** Executes every second with a certain chance to generate a new customer */
    private void tickGenCustomer() {
        if (Math.floor(game.getTimeLeft()) == Math.floor(game.getTimeLeftLastFrame()))
            return;

        if ((float)Math.random() * 100f > game.getCustomerSpawnChance())
            return;

        System.out.println("Spawned Customer");
    }

    public static Game getGame() {
        return game;
    }
    public WorldController getWorldController() {
        return worldController;
    }
    public PlayerController getPlayerController2() {
        return playerController2;
    }

    public void setPlayerController2(PlayerController playerController) {
        playerController2 = playerController;
    }
}
