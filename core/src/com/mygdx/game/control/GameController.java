package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Game;
import com.mygdx.game.view.Main;

/**
 * This class controls the game as all other controllers are instantiated, used and updated here. <br>
 * Just like the Main class, there is only one gameController Object, which is unique in the whole program
 */
public class GameController {
    /** The PlayerController for the first player */
    private final PlayerController playerController1;
    /** The PlayerController for the second player. This one will only be instantiated if the player chose "multiplayer" */
    private PlayerController playerController2;

    /** Controls the customers */
    private final CustomerController customerController;
    /** Controls the customers' orders */
    private final OrderController orderController;

    /** The controller that controls the scenes and the objects visible on them */
    private final WorldController worldController;

    // TODO
    private static Game game;

    public GameController(float roundLength, int payGoal, float customerSpawnChance) {
        game = new Game(roundLength, payGoal, customerSpawnChance);

        // Right now, only the first player is instantiated. Whether the second one will be too, depends on the game mode that the player chooses
        playerController1 = new PlayerController(
                new String[] {
                        "Players/PlayerOne/playerGreenBack.png",
                        "Players/PlayerOne/playerGreenLeft.png",
                        "Players/PlayerOne/playerGreenFront.png",
                        "Players/PlayerOne/playerGreenRight.png"},
                new Vector2(750, 300),
                new int[] { Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT, Input.Keys.ENTER }
        );
        Main.getPlayers()[0] = playerController1.getPlayer();
        playerController2 = null;

        customerController = new CustomerController();
        orderController = new OrderController(new RecipeController());

        worldController = new WorldController();
        worldController.showStart();
    }

    /**
     * Updates the game state for every frame
     * <p>
     * @param dt time
     */
    public void mainLoop(float dt) {
        if (worldController.getSceneID() == 1) {
            playerController1.updateInput(dt);
            if (WorldController.isMultiplayerOn()) playerController2.updateInput(dt);

            tickTime(dt);
            tickGenCustomer();
        }

        worldController.update(dt);
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

    // All Getters
    public static Game getGame() {
        return game;
    }
    public WorldController getWorldController() {
        return worldController;
    }
    public PlayerController getPlayerController2() {
        return playerController2;
    }

    // All Setters
    public void setPlayerController2(PlayerController playerController) {
        playerController2 = playerController;
    }
}
