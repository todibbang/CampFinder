package com.mygdx.campfinder.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
import com.mygdx.campfinder.entities.Camp;
import com.mygdx.campfinder.entities.Profile;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.main.CampFinder;


public class MainMenu extends GameState{
	
	Letters cf = new Letters();
	
	public GameButtonStandard SetupCamp; 
	public GameButtonStandard JoinCamp; 
	public GameButtonStandard Party; 
	public GameButtonStandard Map; 
	
	private TextureRegion bg;
	private String testText = "";
	GL20 gl;
	
	public static Camp thisCamp;
	public static Profile thisProfile;
	
	public static int campAmount = 100;
	public static Camp [] otherCamps;
	
	public MainMenu(GameStateManager gsm) {
		super(gsm);
		
		
		Texture tex = CampFinder.res.getTexture("Background1");		
		bg = new TextureRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
		
		if(thisCamp == null) SetupCamp = new GameButtonStandard("Set Up Camp", cf.x(500), cf.y(900), cam, cf.x(800), cf.y(100), cf.x(50));
		JoinCamp = new GameButtonStandard("Join Camp", cf.x(500), cf.y(700), cam, cf.x(800), cf.y(100), cf.x(50));
		Party = new GameButtonStandard("Party!!", cf.x(500), cf.y(500), cam, cf.x(800), cf.y(100), cf.x(50));
		Map = new GameButtonStandard("Map", cf.x(500), cf.y(300), cam, cf.x(800), cf.y(100), cf.x(50));
		
		Gdx.input.setOnscreenKeyboardVisible(true);
		
		//cam.zoom = 1;
		cam.setToOrtho(false, CampFinder.V_WIDTH * 1, CampFinder.V_HEIGHT * 1);
		
		
	}
	
	public void handleInput() 
	{
		
		//System.out.println(coords);
		
		//testText = Letters.type(testText);
		testText = CampFinder.coordinates[0] + ", " + CampFinder.coordinates[1];
		//System.out.println(MyInput.x +" "+ MyInput.y);
		
		if(thisCamp == null)if(SetupCamp.isReleased())
		{
			SetUpCamp.settingUpNewCamp = true;
			gsm.setState(GameStateManager.SETUPCAMP);
		}
		if(JoinCamp.isReleased())
		{
			
		}
		if(Party.isReleased())
		{
			
		}
		if(Map.isReleased())
		{
			gsm.setState(GameStateManager.MAP);
		}
	}
	
	public void update(float dt) 
	{
		handleInput();
		
		if(thisCamp == null)SetupCamp.update(dt);
		JoinCamp.update(dt);
		Party.update(dt);
		Map.update(dt);
	}
	
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		cam.update();
		
		sb.begin();
		sb.draw(bg, 0, 0, cf.x(1000), cf.y(1000));
		sb.end();
		
		if(thisCamp == null)SetupCamp.render(sb);
		JoinCamp.render(sb);
		Party.render(sb);
		Map.render(sb);
		
		Letters.drawLetter(sb, testText, cf.x(500), cf.y(800), cf.x(30), true, 1000);
	}
	
	public void dispose() {}
}