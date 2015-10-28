package com.mygdx.campfinder.entities;

public class Profile {
	
	public String profileName = "";
	
	public int profilesCampID = 0;
	
	public int profileID = 0;
	
	public int campFollowedsID [] = new int [10000];
	public int campsFollowedAmount = 0;
	
	public Profile(String name, boolean newProfile)
	{
		profileName = name;
		
		
		if(newProfile) 
			while(profileID < 1000000) 
				profileID = ((int) (Math.random() * 9999999));
	}
}
