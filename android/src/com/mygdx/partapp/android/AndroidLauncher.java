package com.mygdx.partapp.android;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.mygdx.campfinder.main.CampFinder;

public class AndroidLauncher extends AndroidApplication implements
		GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
		LocationListener
{
	private int numse = 8;

	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;


	@Override
	protected void onCreate (Bundle savedInstanceState) 
	{
		System.out.println();
		
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		CampFinder.androidLaunch = true;

		Log.d("party", "Create");
		
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

		mLocationRequest = LocationRequest.create()
				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
				.setInterval(10 * 1000) // 10 seconds
				.setFastestInterval(1 * 1000); // 1 second

		initialize(new CampFinder(new AndroidGPS()), config);
		
		//Intent intent = new Intent(this, AndroidGPS.class);
		//startActivity(intent);
	}

	@Override
	protected void onResume ()
	{
		super.onResume();
		mGoogleApiClient.connect();
	}

		@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Log.d("Location", "Test 3");
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Log.d("Location", "Test 1");

		LocationServices.FusedLocationApi.requestLocationUpdates
				(mGoogleApiClient, mLocationRequest, this);
		//Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		//AndroidGPS.latitude = location.getLatitude();
		//AndroidGPS.longitude = location.getLongitude();
		//Log.d("Location", location.toString());
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		Log.d("Location", "Test 2");
	}

	@Override
	public void onLocationChanged(Location location)
	{

		AndroidGPS.latitude = location.getLatitude();
		AndroidGPS.longitude = location.getLongitude();
	}
}