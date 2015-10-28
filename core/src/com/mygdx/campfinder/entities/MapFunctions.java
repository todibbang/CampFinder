package com.mygdx.campfinder.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.campfinder.handlers.GameButtonStandard;
import com.mygdx.campfinder.handlers.Letters;
import com.mygdx.campfinder.handlers.MyInput;
import com.mygdx.campfinder.main.CampFinder;

public class MapFunctions {
	
	Letters cf = new Letters();

	public float currentPosX = ((float) CampFinder.coordinates[0]);
	public float currentPosY = ((float) CampFinder.coordinates[1]);
	
	public GameButtonStandard ZoomInButton;
	public GameButtonStandard ZoomOutButton;
	
	public boolean mousePositionBoolean = false;
	
	public int cameraPositionX;
	public int cameraPositionY;
	
	public int cameraPositionStartX = cameraPositionX;
	public int cameraPositionStartY = cameraPositionY;
	
	public int mousePositionStartX;
	public int mousePositionStartY;
	
	public int mouseDragStartX;
	public int mouseDragStartY;
	
	public boolean ScreenMoved = false;
	public int ScreenMovedCD = 0;
		
	public boolean doubleTouchZoomStarted = false;
	public double doubleTouchZoomStartDistance = 0;
	public double doubleTouchZoomNewDistance = 0;
		
	public OrthographicCamera camMap;
	
	public Texture currentPosition = CampFinder.res.getTexture("CurrentLocation");
	
	public Vector3 vec = new Vector3();
	public Vector3 vecTouch [] = new Vector3 [5];
	
	public MapFunctions(OrthographicCamera cam)
	{
		for(int i = 0; i < 5; i++)
		{
			vecTouch[i] = new Vector3();
		}
		
		ZoomInButton = new GameButtonStandard("+", cf.x(920), cf.y(150), cam, cf.x(80), cf.x(80), cf.x(40));
		ZoomOutButton = new GameButtonStandard("-", cf.x(920), cf.y(60), cam, cf.x(80), cf.x(80), cf.x(40));
		

		camMap = new OrthographicCamera();
		camMap.setToOrtho(false, cf.x(1000), cf.y(1000));
		camMap.zoom = 5;
	}
	
	public boolean handleHUDInput( boolean action)
	{
		if(ZoomInButton.isReleased())
		{
			camMap.zoom -= 0.5f;
			action = true;
		}
		else if(ZoomOutButton.isReleased()) 
		{
			camMap.zoom += 0.5f;
			action = true;
		}
		return action;
	}
	public void handleMapInput()
	{
		CamUpdater();
	}
	
	
	public void update(float dt, OrthographicCamera cam)
	{
		vec.set(MyInput.x, MyInput.y, 0);
		if(cam != null){
		cam.unproject(vec);
		}
		
		for(int i = 0; i < 5; i++)
		{
			vecTouch[i].set(MyInput.touches[i].touchX, MyInput.touches[i].touchY, 0);
			if(cam != null) {
				cam.unproject(vecTouch[i]);
			}
		}
		
		currentPosX = ((float) CampFinder.coordinates[0]);
		currentPosY = ((float) CampFinder.coordinates[1]);
		
		ZoomInButton.update(dt);
		ZoomOutButton.update(dt);
	}
	
	public void mapCamRender(SpriteBatch sb)
	{
		sb.begin();
		sb.draw(currentPosition, currentPosX - (currentPosition.getWidth()/2), currentPosY - (currentPosition.getHeight()/2));
		sb.end();
	}
	
	public void camRender(SpriteBatch sb)
	{
		ZoomInButton.render(sb);
		ZoomOutButton.render(sb);
		
		//Letters.drawLetter(sb, "pixel: " + currentPosX + " " + currentPosY, cf.x(500), cf.y(900), cf.x(30), true, 100000);
		//Letters.drawLetter(sb, "coords: " + CampFinder.coordinates[1] + " " + CampFinder.coordinates[0] , cf.x(500), cf.y(800), cf.x(30), true, 100000);
	}
	
	
	private void CamUpdater()
	{
		if(MyInput.isReleased())
		{
			cameraPositionStartX = cameraPositionX;
			cameraPositionStartY = cameraPositionY;
			ScreenMovedCD = 20;
		}
		
		if(ScreenMovedCD > 0) ScreenMovedCD --;
		if(ScreenMovedCD == 0) ScreenMoved = false;
		
		if(MyInput.scroll != 0) camMap.zoom += (0.5f * MyInput.scroll);
		
		
		
		if(MyInput.isDown(MyInput.Z))
		{
			if(doubleTouchZoomStarted != true)
			{
				doubleTouchZoomStartDistance = Math.sqrt(Math.pow(vec.x - vec.x, 2) +
						Math.pow(vec.y - 0, 2));
				doubleTouchZoomStarted = true;
			}
			else
			{
				doubleTouchZoomNewDistance = Math.sqrt(Math.pow(vec.x - vec.x, 2) +
						Math.pow(vec.y - 0, 2));
				camMap.zoom += (doubleTouchZoomStartDistance - doubleTouchZoomNewDistance) / 100f;
				doubleTouchZoomStartDistance = doubleTouchZoomNewDistance;
			}
		}
		else doubleTouchZoomStarted = false;
		
		
		/*
		if(MyInput.touches[0].touched && MyInput.touches[1].touched)
		{
			if(doubleTouchZoomStarted != true)
			{
				doubleTouchZoomStartDistance = Math.sqrt(Math.pow(vecTouch[0].x - vecTouch[1].x, 2) +
						Math.pow(vecTouch[0].y - vecTouch[1].y, 2));
				doubleTouchZoomStarted = true;
			}
			else
			{
				doubleTouchZoomNewDistance = Math.sqrt(Math.pow(vecTouch[0].x - vecTouch[1].x, 2) +
						Math.pow(vecTouch[0].y - vecTouch[1].y, 2));
				camMap.zoom += (doubleTouchZoomStartDistance - doubleTouchZoomNewDistance);
				doubleTouchZoomStartDistance = doubleTouchZoomNewDistance;
			}
		}
		else doubleTouchZoomStarted = false;
		*/
		
		
		if(camMap.zoom < 0.5f) camMap.zoom = 0.5f;
		camMap.position.set(cameraPositionX, cameraPositionY, 0);
		
		if(MyInput.isPressed())
		{
			mousePositionStartX = ((int) (vec.x * camMap.zoom));
			mousePositionStartY = ((int) (vec.y * camMap.zoom));
		}
		if(MyInput.isDown() && mouseDragged((int) (20 * camMap.zoom)))
		{
			cameraPositionX = cameraPositionStartX - (((int) (vec.x * camMap.zoom)) - mousePositionStartX);
			cameraPositionY = cameraPositionStartY - (((int) (vec.y * camMap.zoom)) - mousePositionStartY);
			ScreenMoved = true;
		}
	}
	public boolean mouseDragged(int distanceRequested)
	{
		boolean dragged = false;
		double draggedDistance = Math.sqrt(Math.pow((vec.x * camMap.zoom) - mousePositionStartX, 2) +
				Math.pow((vec.y * camMap.zoom) - mousePositionStartY, 2));
		if(draggedDistance > distanceRequested) dragged = true;
		return dragged;
	}
	
	
}
