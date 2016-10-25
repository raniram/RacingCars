package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MyGame extends Game {
	public final static int WIDTH = 2000;
	public final static int HEIGHT = 1000;
	private GameScreen gameScreen;
	private Music traffic;

	@Override
	public void create() {
		Assets.load();
		gameScreen = new GameScreen();
		//gameScreen.resize(WIDTH,HEIGHT);
		setScreen(gameScreen);
		traffic= Gdx.audio.newMusic(Gdx.files.internal("traffic.wav"));
		traffic.setLooping(true);
		//traffic.play();

	}

	@Override
	public void dispose() {
		Assets.dispose();
		gameScreen.dispose();
	}
}
