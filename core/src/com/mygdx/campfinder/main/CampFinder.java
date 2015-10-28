package com.mygdx.campfinder.main;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.campfinder.handlers.Content;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.MyInput;
import com.mygdx.campfinder.handlers.MyInput.touchInfo;
import com.mygdx.campfinder.handlers.MyInputProcessor;
import com.mygdx.campfinder.states.GameState;



public class CampFinder implements ApplicationListener{
	
	public final GetGPSCoordinates coords;
	
	
	public CampFinder(GetGPSCoordinates coords)
	{
		this.coords = coords;
	}
	
	
	public static final String TITLE = "Camp Finder";
	public static int V_WIDTH = 500;
	public static int V_HEIGHT = 800;
	public static final int SCALE = 1;
	//public static final int CAMZOOM = 1;
	public static final boolean VSYNC = true;
	
	public static boolean androidLaunch = false;
	
	public static final float STEP = 1 / 60f;
	
	
	public static double [] coordinates ;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create(){
		//Texture.se
		//Texture.setEnforcePotImages(false);
		
		if(androidLaunch)
		{
			V_WIDTH = Gdx.graphics.getWidth();
			V_HEIGHT = Gdx.graphics.getHeight();
			
		}
		
		
		
		res = new Content();
		
		res.loadTexture("images/Campfinder/StartupScreen.png", "StartupScreen");
		res.loadTexture("images/Campfinder/Background1.png", "Background1");
		res.loadTexture("images/Campfinder/Button.png", "Button");
		res.loadTexture("images/Campfinder/Kort.png", "Map");
		res.loadTexture("images/Campfinder/NewCamp.png", "NewCamp");
		res.loadTexture("images/Buttons/Letters.png", "Letters");
		
		res.loadTexture("images/Campfinder/CurrentLocation.png", "CurrentLocation");
		res.loadTexture("images/Campfinder/InspectBg.png", "InspectBg");
		
		res.loadTexture("images/Campfinder/MAP-DAY.png", "MapDay");
		res.loadTexture("images/Campfinder/MAP-NIGHT.png", "MapNight");
		
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH , V_HEIGHT );
		gsm = new GameStateManager(this);
		for(int i = 0; i < 5; i++) MyInput.touches[i] = new touchInfo();
		
}
	
	
	public void render(){
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		MyInput.update();
		
		coordinates = this.coords.GetCorrdinates();
	}
	
	public void dispose(){
		
	}
	
	public SpriteBatch getSpriteBatch(){
		return sb;
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
	
	public interface GetGPSCoordinates 
	{
		   public double [] GetCorrdinates();
	}
	
	public double [] getGPScoordinates(){
		return this.coords.GetCorrdinates();
	}
	
	
	
}