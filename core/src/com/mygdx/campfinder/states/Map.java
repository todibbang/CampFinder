package com.mygdx.campfinder.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.campfinder.entities.MapFunctions;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.GameStateManager;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.handlers.TypeField;
import com.mygdx.campfinder.main.CampFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Map extends GameState{
	
	public Texture bgKBH = CampFinder.res.getTexture("Map");		
	public Texture bgDay = CampFinder.res.getTexture("MapDay");		
	public Texture bgNight = CampFinder.res.getTexture("MapNight");		
	
	// camera stuff
	private MapFunctions mf = new MapFunctions(cam);
	Letters cf = new Letters();
	public Texture yourCamp = CampFinder.res.getTexture("NewCamp");
	
	public TypeField searchField = new TypeField(cf.x(500), cf.y(900), cam, cf.x(700), cf.x(100), cf.x(40), true, true);
	
	private GameButtonStandard filterFollowedButton = 
			new GameButtonStandard("Followed", cf.x(100), cf.y(50), cam, cf.x(160), cf.x(70), cf.x(20));
	private boolean filterFollowed = false;
	
	private boolean filterParty = false;
	private GameButtonStandard filterPartyButton = 
			new GameButtonStandard("Party", cf.x(300), cf.y(50), cam, cf.x(160), cf.x(70), cf.x(20));
	
	private GameButtonStandard campButton [] = new GameButtonStandard[MainMenu.campAmount];
	private int campAmount = MainMenu.campAmount;
	private boolean drawCamp [] = new boolean [campAmount];
	
	private boolean inspecting = false;
	public Texture InspectBG = CampFinder.res.getTexture("InspectBg");
	private String inspectName = "";
	private String inspectDescription = "";
	private int inspectCampID = 0;
	private boolean inspectedCampfollowed = false;
	private GameButtonStandard followInspect;
	
	public String searchText = "";
	
	public Map(GameStateManager gsm) {
		super(gsm);
		
		Gdx.input.setOnscreenKeyboardVisible(false);
		
		for(int i = 0; i < campAmount; i++)
		{
			campButton[i] = new GameButtonStandard("Camp", 
					MainMenu.otherCamps[i].campLocationX, 
					MainMenu.otherCamps[i].campLocationY, mf.camMap, 50, 50, 10);
		}
		followInspect = new GameButtonStandard("follow", cf.x(400), cf.y(550), cam, cf.x(200), cf.x(70), cf.x(30));
	}
	
	@Override
	public void handleInput() {
		boolean action = handleHUDInput();
		if(action != true) handleMapInput();
		
	}
	
	public boolean handleHUDInput()
	{
		boolean action = false;
		
		filterUpdater();
		
		if(filterFollowedButton.isReleased())
		{
			if(filterFollowed) filterFollowed = false;
			else filterFollowed = true;
			action = true;
		}
		else if(filterPartyButton.isReleased())
		{
			if(filterParty) filterParty = false;
			else filterParty = true;
			action = true;
		}
		else if(searchField.isReleased())
		{
			action = true;
		}
		else if(followInspect.isReleased() && inspecting)
		{
			updateFilterButton(true);
			action = true;
		}
		
		action = mf.handleHUDInput(action);
		
		return action;
	}
	
	
	public void handleMapInput() 
	{
		
		for(int i = 0; i < campAmount; i++)
		{
			if(campButton[i].isReleased() && drawCamp[i])
			{
				inspecting = true;
				inspectName = MainMenu.otherCamps[i].campName;
				inspectDescription = MainMenu.otherCamps[i].campDescription;
				inspectCampID = MainMenu.otherCamps[i].campID;
				inspectedCampfollowed = false;
				
				updateFilterButton(false);
				
			}
		}
		
		if(mf.ScreenMoved) inspecting = false;
		
		mf.handleMapInput();
	}
	
	public void update(float dt) 
	{
		handleInput();
		mf.update(dt, cam);
		
		for(int i = 0; i < campAmount; i++)
		{
			campButton[i].update(dt);
		}
		
		searchField.update(dt);
		filterPartyButton.update(dt);
		filterFollowedButton.update(dt);
		followInspect.update(dt);
	}
	
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(mf.camMap.combined);
		mf.camMap.update();
		sb.begin();
		//if(filterParty)sb.draw(bgNight, 0, 0);
		//else sb.draw(bgDay, 0, 0);
		sb.draw(bgKBH, 0, 0);
		sb.end();
		//Render the stuff that goes on the Map
		
		for(int i = 0; i < campAmount; i++)
		{
			
			if(drawCamp[i])
			{
				sb.setColor(MainMenu.otherCamps[i].color);
				campButton[i].render(sb);
				sb.setColor(Color.WHITE);
			}
		}
		
		sb.begin();
		if(MainMenu.thisCamp != null) sb.draw(yourCamp, MainMenu.thisCamp.campLocationX, MainMenu.thisCamp.campLocationX);
		sb.end();
		
		mf.mapCamRender(sb);
		sb.setProjectionMatrix(cam.combined);
		cam.update();
		//Render the HUD (buttons n stuff)
		filterPartyButton.render(sb);
		filterFollowedButton.render(sb);
		
		searchField.render(sb);
		
		if(inspecting)
		{
			sb.begin();
			sb.draw(InspectBG, cf.x(100), cf.y(500), cf.x(800), cf.y(300));
			sb.end();
			followInspect.render(sb);
			Letters.drawLetter(sb, inspectName, cf.x(150), cf.y(750), cf.x(25), false, cf.x(1000));
			Letters.drawLetter(sb, inspectDescription, cf.x(150), cf.y(680), cf.x(20), false, cf.x(1000));
		}
			
			
		mf.camRender(sb);
		
		
	}
	
	public void dispose() {}
	
	public void filterUpdater()
	{
		searchText = searchField.buttonText;
		for(int i = 0; i < campAmount; i++)
		{
			if(searchText != "")
			{
				if(MainMenu.otherCamps[i].campName.contains(searchText)) drawCamp[i] = true;
				else drawCamp[i] = false;
			}
			else drawCamp[i] = true;
			
			if(filterFollowed || filterParty) drawCamp[i] = false;
			
			if(filterFollowed)
			{
				
				for(int f = 0; f < MainMenu.thisProfile.campsFollowedAmount; f++)
				{
					if(MainMenu.thisProfile.campFollowedsID[f] == MainMenu.otherCamps[i].campID) drawCamp[i] = true;
				}
			}
			
			if(filterParty)
			{
				if(MainMenu.otherCamps[i].party) drawCamp[i] = true;
			}
		}
	}
	
	public void updateFilterButton(boolean clicked)
	{
		for(int f = 0; f < MainMenu.thisProfile.campsFollowedAmount; f++)
		{
			followInspect = new GameButtonStandard("follow", cf.x(400), cf.y(550), cam, cf.x(200), cf.x(70), cf.x(30));
			
			if(MainMenu.thisProfile.campFollowedsID[f] == inspectCampID)
			{
				followInspect = new GameButtonStandard("unfollow", cf.x(400), cf.y(550), cam, cf.x(200), cf.x(70), cf.x(30));
				break;
			}
		}
		
		if(clicked)
		{
			boolean follow = true;
			for(int f = 0; f < MainMenu.thisProfile.campsFollowedAmount; f++)
			{
				if(MainMenu.thisProfile.campFollowedsID[f] == inspectCampID) follow = false;
			}
			
			if(follow)
			{
				MainMenu.thisProfile.campFollowedsID[MainMenu.thisProfile.campsFollowedAmount] = inspectCampID;
				MainMenu.thisProfile.campsFollowedAmount ++;
				followInspect = new GameButtonStandard("unfollow", cf.x(400), cf.y(550), cam, cf.x(200), cf.x(70), cf.x(30));
				
				System.out.println("Camp was followed");
			}
			else 
			{
				boolean replaceFurther = false;
				for(int f = 0; f < MainMenu.thisProfile.campsFollowedAmount; f++)
				{
					if(replaceFurther) MainMenu.thisProfile.campFollowedsID[f - 1] = MainMenu.thisProfile.campFollowedsID[f];
					else
					{
						if(MainMenu.thisProfile.campFollowedsID[f] == inspectCampID) replaceFurther = true;
					}
				}
				MainMenu.thisProfile.campsFollowedAmount--;
				followInspect = new GameButtonStandard("follow", cf.x(400), cf.y(550), cam, cf.x(200), cf.x(70), cf.x(30));
				System.out.println("Camp was un-followed");
			}
		}
	}
}