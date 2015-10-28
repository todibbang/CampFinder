package com.mygdx.campfinder.handlers;

import java.util.Stack;

import com.mygdx.campfinder.main.CampFinder;
import com.mygdx.campfinder.states.GameState;
import com.mygdx.campfinder.states.MainMenu;
import com.mygdx.campfinder.states.Map;
import com.mygdx.campfinder.states.SetUpCamp;
import com.mygdx.campfinder.states.SetUpCampMap;
import com.mygdx.campfinder.states.StartUpScreen;

public class GameStateManager {
	
	private CampFinder CF;
	
	private Stack<GameState> gameStates;
	
	public static final int STARTUPSCREEN = 7335461;
	public static final int MAINMENU = 325435;
	public static final int SETUPCAMP = 583059;
	public static final int SETUPCAMPMAP = 483059;
	public static final int MAP = 586489;
	
	public GameStateManager(CampFinder CF) {
		this.CF = CF;
		gameStates = new Stack<GameState>();
		pushState(STARTUPSCREEN);
	}
	
	public CampFinder CF(){
		return CF;
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	private GameState getState(int state){
		if(state == MAINMENU)
			return new MainMenu(this);
		if(state == SETUPCAMP)
			return new SetUpCamp(this);
		if(state == SETUPCAMPMAP)
			return new SetUpCampMap(this);
		if(state == STARTUPSCREEN)
			return new StartUpScreen(this);
		if(state == MAP)
			return new Map(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}
}
