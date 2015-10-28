package com.mygdx.campfinder.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.campfinder.entities.MapFunctions;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.handlers.MyInput;
import com.mygdx.campfinder.main.CampFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SetUpCampMap extends GameState{
	
	// camera stuff
	
	private MapFunctions mf = new MapFunctions(cam);
	
	Letters cf = new Letters();

	public GameButtonStandard SetCamp;
	
	private float newCampPositionX = mf.currentPosX;
	private float newCampPositionY = mf.currentPosY;
			
	private TextureRegion bg;
	//private TextureRegion NewCamp = ;
	public Texture NewCamp = CampFinder.res.getTexture("NewCamp");
	
	public SetUpCampMap(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = CampFinder.res.getTexture("Map");		
		bg = new TextureRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
		
		SetCamp = new GameButtonStandard("Set Camp", cf.x(500), cf.y(100), cam, cf.x(700), cf.y(50), cf.x(40));
		
		
		
	}
	
	public void handleInput() 
	{
		boolean input = false;
		input = mf.handleHUDInput(input);
		if(input != true)
		{
			mf.handleMapInput();
			
			if(SetCamp.isReleased())
			{
				SetUpCamp.newCampLocationX = newCampPositionX;
				SetUpCamp.newCampLocationY = newCampPositionY;
				
				SetUpCamp.createNewCamp();
				gsm.setState(GameStateManager.MAINMENU);
			}
			else if(MyInput.isReleased() && mf.ScreenMoved != true)
			{
				newCampPositionX = mf.camMap.position.x - (cf.x(500) * mf.camMap.zoom) + (mf.vec.x * mf.camMap.zoom);
				newCampPositionY = mf.camMap.position.y - (cf.y(500) * mf.camMap.zoom) + (mf.vec.y * mf.camMap.zoom);
				//newCampPositionX = mf.camMap.position.x - (cf.x(500) * mf.camMap.zoom) + (MyInput.x * mf.camMap.zoom);
				//newCampPositionY = mf.camMap.position.y - (cf.y(500) * mf.camMap.zoom) + ((cf.y(1000) - MyInput.y) * mf.camMap.zoom);
			}
		}
		
	}
	
	public void update(float dt) 
	{
		handleInput();
		SetCamp.update(dt);
		
		mf.update(dt, cam);
		
	}
	
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(mf.camMap.combined);
		mf.camMap.update();
		//Render the stuff that goes on the Map
		sb.begin();
		sb.draw(bg, 0, 0);
		
		sb.setColor(Color.RED);
		sb.draw(NewCamp, newCampPositionX, newCampPositionY);
		sb.setColor(Color.WHITE);
		sb.end();
		
		mf.mapCamRender(sb);
		sb.setProjectionMatrix(cam.combined);
		cam.update();
		//Render the HUD (buttons n stuff)
		SetCamp.render(sb);
		
		
		mf.camRender(sb);
		
		
	}
	
	public void dispose() {}
}