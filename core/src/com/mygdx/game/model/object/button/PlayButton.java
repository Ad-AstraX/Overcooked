package com.mygdx.game.model.object.button;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.control.GameController;
import com.mygdx.game.control.PlayerController;
import com.mygdx.game.control.WorldController;
import com.mygdx.game.view.Main;

/**
 * This is the button that prompts the actual game to start and the kitchenScene to be created.
 * This is also instantiated once during the creation of the Game UI.
 */
public class PlayButton extends Button {
    public PlayButton(Vector2 position, Vector2 size) {
        super(new String[] {"Buttons/playButton.png", "Buttons/playButtonSelected.png"}, position, size);
    }

    public boolean checkForInteraction() {
        if (mouseOnButton()) {
            if (this.texture.toString().equals("Textures/" + textures[0])) this.setTexture(textures[1]);
            if (mouseClicked()) {
                // prepare
                GameController.singleton.getPlayerController1().getPlayer().setPosition(new Vector2(750, 300));
                if (GameController.singleton.getPlayerController1().getPlayer() != null)
                    GameController.singleton.getPlayerController1().getPlayer().setHand(null);
                GameController.singleton.getGame().setPayTotal(0);
                GameController.singleton.getGame().setTimeLeft(Main.singleton.getMaxGameTime());

                GameController.singleton.getCustomerController().emptyQueue();

                GameController.singleton.getWorldController().setTransitionDarker(true);
                clickedSound.play(0.5f);

                if (WorldController.isMultiplayerOn()) {
                    Main.getGameController().setPlayerController2(new PlayerController(
                            new String[]{
                                    "Players/PlayerTwo/playerOrangeBack.png",
                                    "Players/PlayerTwo/playerOrangeLeft.png",
                                    "Players/PlayerTwo/playerOrangeFront.png",
                                    "Players/PlayerTwo/playerOrangeRight.png"},
                            new Vector2(750, 500),
                            new int[]{ Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT, Input.Keys.ENTER }
                    ));
                    Main.getPlayers()[1] = GameController.singleton.getPlayerController2().getPlayer();
                }
                return true;
            }
        } else if (this.texture.toString().equals("Textures/" + textures[1])) this.setTexture(textures[0]);
        return false;
    }
}
