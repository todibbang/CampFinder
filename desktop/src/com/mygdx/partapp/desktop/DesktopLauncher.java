package com.mygdx.partapp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.campfinder.main.CampFinder;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = CampFinder.TITLE;
		config.width = CampFinder.V_WIDTH * CampFinder.SCALE;
		config.height = CampFinder.V_HEIGHT * CampFinder.SCALE;
		config.vSyncEnabled = CampFinder.VSYNC;
		
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new CampFinder(new DesktopGPSCoordinates()), config);
		
		new LwjglApplication(new CampFinder(new DesktopGPSCoordinates()), config);
	}

}