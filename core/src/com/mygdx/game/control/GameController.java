package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.BackgroundObject;
import com.mygdx.game.model.Game;
import com.mygdx.game.view.Main;

/**
 * This class controls the game as all other controllers are instantiated, used and updated here. <br>
 * Just like the Main class, there is only one gameController Object, which is unique in the whole program
 */
public class GameController {
    /** For global access */
    public static GameController singleton;

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
    private static BackgroundObject gameMessage;

    public GameController(float roundLength, int payGoal, float customerSpawnChance) {
        if (singleton == null)
            singleton = this;

        game = new Game(roundLength, payGoal, customerSpawnChance);

        // Right now, only the first player is instantiated. Whether the second one will be too, depends on the game mode that the player chooses
        playerController1 = new PlayerController(
                new String[] {
                        "Players/PlayerOne/playerGreenBack.png",
                        "Players/PlayerOne/playerGreenLeft.png",
                        "Players/PlayerOne/playerGreenFront.png",
                        "Players/PlayerOne/playerGreenRight.png"},
                new Vector2(750, 300),
                new int[] { Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D, Input.Keys.E }
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
        if (worldController.getSceneID() == 1 && game.getTimeLeft() >= 0) {
            playerController1.updateInput(dt);
            if (WorldController.isMultiplayerOn())
                playerController2.updateInput(dt);

            tickTime(dt);
            tickGenCustomer();

            customerController.UpdateCustomerAndOrder(dt);
        } else if (game.getTimeLeft() <= 0) moveGameMessage(dt);

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

        if (game.getTimeLeft() <= 0 || game.getPayTotal() >= game.getPayGoal()) {
            if (game.getTimeLeft() <= 0) {
                gameMessage = new BackgroundObject("Other/lossGameMessage.png", new Vector2(1950/2f-380, 1675), new Vector2(380*2, 500));
            } else {
                gameMessage = new BackgroundObject("Other/winGameMessage.png", new Vector2(1950/2f-38, 1550), new Vector2(380*2, 500));
            }
            Main.getStaticObjectLists()[1].append(gameMessage);

            //TODO wait a little
            worldController.setTransitionDarker(true);
            worldController.setSceneID(0);
        }
    }

    private void moveGameMessage(float dt) {
        if (gameMessage.getPosition().equals(new Vector2(1950/2f-380, 1425/2f-250)))
            return;

        Vector2 movementTarget = new Vector2().add(new Vector2(1950/2f-380, 1425/2f-250));
        Vector2 newPosition = new Vector2(
                Interpolation.pow2InInverse.apply(gameMessage.getPosition().x, movementTarget.x, dt * 0.25f),
                Interpolation.pow2InInverse.apply(gameMessage.getPosition().y, movementTarget.y, dt * 0.25f)
        );
        gameMessage.setPosition(newPosition);
    }


    /** Executes every frame with a certain chance to generate a new customer */
    private void tickGenCustomer() {
        if (Math.floor(game.getTimeLeft()) == Math.floor(game.getTimeLeftLastFrame()))
            return;

        if ((float)Math.random() * 100f > game.getCustomerSpawnChance())
            return;

        customerController.generateNewCustomer(orderController);
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
    public CustomerController getCustomerController() {
        return customerController;
    }
    public PlayerController getPlayerController1() {
        return playerController1;
    }

    // All Setters
    public void setPlayerController2(PlayerController playerController) {
        playerController2 = playerController;
    }
}
