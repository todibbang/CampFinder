package com.mygdx.campfinder.entities;

import com.badlogic.gdx.graphics.Color;

public class Camp {
	
	public String campName = "";
	public String campDescription = "";
	public float campLocationX = 0;
	public float campLocationY = 0;
	
	public boolean party = false;
	
	public int campID = 0;
	public Color color = Color.WHITE;
	
	public Camp(String name, String description, float x, float y, boolean newCamp)
	{
		campName = name;
		campDescription = description;
		campLocationX = x;
		campLocationY = y;
		
		
		
		if(newCamp) 
			while(campID < 1000000) 
				campID = ((int) (Math.random() * 9999999));
	}
}
