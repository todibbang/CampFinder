package com.mygdx.campfinder.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.main.CampFinder;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected CampFinder CF;
	protected SpriteBatch sb;
	public static OrthographicCamera cam;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		CF = gsm.CF();
		sb = CF.getSpriteBatch();
		cam = CF.getCamera();
	}
		
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();

	public SpriteBatch GetSB() {
		return sb; 
	}
}