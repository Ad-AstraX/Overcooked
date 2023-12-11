package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.control.GameController;

public class Main extends ApplicationAdapter {
	GameController gameController;

	@Override
	public void create() {
		gameController = new GameController(120f, 60, 1f);

	}

	@Override
	public void render() {
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(1, 0, 0, 1);
	}

	@Override
	public void dispose() {

	}
}
