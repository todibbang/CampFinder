                                            package com.mygdx.campfinder.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.campfinder.entities.Camp;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.handlers.TypeField;
import com.mygdx.campfinder.main.CampFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SetUpCamp extends GameState{
	
	Letters cf = new Letters();
	
	public TypeField CampName;
	public TypeField YourName;
	public TypeField CampDescription;
	public GameButtonStandard SetCamp;
	public static boolean settingUpNewCamp = false;
	
	public static String newCampName = "";
	public static String newCampDescription = "";
	public static float newCampLocationX = 0;
	public static float newCampLocationY = 0;
	
	private TextureRegion bg;
	
	public SetUpCamp(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = CampFinder.res.getTexture("Background1");		
		bg = new TextureRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
		
		CampName = new TypeField(cf.x(500), cf.y(900), cam, cf.x(700), cf.y(50), cf.x(40), true, true);
		YourName = new TypeField(cf.x(500), cf.y(700), cam, cf.x(700), cf.y(50), cf.x(40), true, true);
		CampDescription = new TypeField(cf.x(500), cf.x(500), cam, cf.x(700), cf.y(200), cf.x(40), false, false);
		
		SetCamp = new GameButtonStandard("Set Camp", cf.x(500), cf.y(50), cam, cf.x(700), cf.y(50), cf.x(40));
		
		
		Gdx.input.setOnscreenKeyboardVisible(false);
	}
	
	public void handleInput() 
	{
		if(SetCamp.isReleased())
		{
			gsm.setState(GameStateManager.SETUPCAMPMAP);
		}
	}
	
	public void update(float dt) 
	{
		handleInput();
		
		CampName.update(dt);
		YourName.update(dt);
		CampDescription.update(dt);
		SetCamp.update(dt);
		
		newCampName = CampName.buttonText;
		newCampDescription = CampDescription.buttonText;
	}
	
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		cam.update();
		
		sb.begin();
		sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sb.end();
		
		Letters.drawLetter(sb, "Set Up Camp", 150, 530, 1, true, 1000);
		Letters.drawLetter(sb, "Camp Name", 150, 480, 0.6f, true, 1000);
		CampName.render(sb);
		Letters.drawLetter(sb, "Your Name", 150, 410, 0.6f, true, 1000);
		YourName.render(sb);
		
		CampDescription.render(sb);
		
		SetCamp.render(sb);
	}
	
	public void dispose() {}
	
	public static void createNewCamp()
	{
		settingUpNewCamp = false;
		MainMenu.thisCamp = new Camp(newCampName, newCampDescription, newCampLocationX, newCampLocationY, true);
		
		MainMenu.thisProfile.profilesCampID = MainMenu.thisCamp.campID;
	}
}