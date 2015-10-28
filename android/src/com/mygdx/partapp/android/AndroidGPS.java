
package com.mygdx.partapp.android;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.mygdx.campfinder.main.CampFinder.GetGPSCoordinates;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log; 

public class AndroidGPS extends Activity implements GetGPSCoordinates{
	
	public static double latitude = 0;
	public static double longitude = 0;
	
	
	@Override 
	public double [] GetCorrdinates() {
		
		
		
		double coords [] = {(longitude - 12.538819f) * 135380.7584f, (latitude - 55.691121f) * 240186.8120f};

		 // TODO Auto-generated method stub
		return coords;
	}
}