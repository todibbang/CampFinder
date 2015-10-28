                                            package com.mygdx.campfinder.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.mygdx.campfinder.entities.Camp;
import com.mygdx.campfinder.entities.Profile;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.handlers.MyInput;
import com.mygdx.campfinder.handlers.MyInputProcessor;
import com.mygdx.campfinder.main.CampFinder;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.GroupStrategy;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.UBJsonReader;


public class StartUpScreen extends GameState{
	
	private int StartTimer = 0;
	
	// create a decal sprite
	public Decal decalSprite;
	public Decal decalSprite1;
	public static DecalBatch db;
	public CameraGroupStrategy cameraGroupStrategy;
	private TextureRegion bg;
	
	GL20 gl;
	
	public StartUpScreen(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = CampFinder.res.getTexture("StartupScreen");	
		bg = new TextureRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
		
		MainMenu.thisProfile = new Profile("Tobias", true);
		
		MainMenu.otherCamps = new Camp [100];
		tex = CampFinder.res.getTexture("Map");
		for(int i = 0; i < MainMenu.campAmount; i++)
		{
			
			float x = (float) (Math.random() * tex.getWidth());
			float y = (float) (Math.random() * tex.getHeight());
			
			MainMenu.otherCamps[i] = new Camp("camp " + i, "A random generated camp", x, y, true);
			
			int party = (int) (Math.random() * 7);
			if(party == 0) MainMenu.otherCamps[i].party = true;
			if(party == 0) MainMenu.otherCamps[i].color = Color.BLUE;
			if(party == 1) MainMenu.otherCamps[i].color = Color.RED;
			if(party == 2) MainMenu.otherCamps[i].color = Color.CORAL;
			if(party == 3) MainMenu.otherCamps[i].color = Color.GREEN;
			if(party == 4) MainMenu.otherCamps[i].color = Color.YELLOW;
			if(party == 5) MainMenu.otherCamps[i].color = Color.BLACK;
			if(party == 6) MainMenu.otherCamps[i].color = Color.PURPLE;
			
		}
		
		cam.zoom = 1;
		cam.setToOrtho(false, CampFinder.V_WIDTH, CampFinder.V_HEIGHT);
	}
	
	public void handleInput() 
	{
		StartTimer ++;
		
		if(StartTimer > 20)
		{
			
			
			
			gsm.setState(GameStateManager.MAINMENU);
		}
	}
	
	public void update(float dt) 
	{
		handleInput();	    
	}
	
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		
		//cam.zoom = 2;
		cam.update();
		//cam.apply(Gdx.gl10);
		//sb.setProjectionMatrix(cam.combined);
		
		sb.begin();
		sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sb.end();
			
	}
	
	public void dispose() {}
	
	
	
	
}