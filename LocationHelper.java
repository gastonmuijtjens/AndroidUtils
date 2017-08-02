package com.company.product.models.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.Locale;

/**
 * Helper class for getting location
 *
 * @author Gaston Muijtjens
 * @since 22-05-2017
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks, LocationListener {
	
	private GoogleApiClient apiClient;
	private Activity activity;
	
	/**
	 * @param activity The Activity to perform the location updates from
	 */
	public LocationHelper(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * Checks if the permissions are granted for getting the location
	 *
	 * @return Boolean indicating whether the permissions are granted or not
	 */
	public boolean hasPermissions() {
		return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
	}
	
	/**
	 * Will start collecting the location updates
	 * If the user did not granted permission, this function will do nothing
	 */
	public void requestLocationUpdates() {
		if (!hasPermissions()) {
			return;
		}
		
		GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
		int isAvailable = apiAvailability.isGooglePlayServicesAvailable(activity);
		
		if (isAvailable == ConnectionResult.SUCCESS) {
			apiClient = new GoogleApiClient.Builder(activity).addApi(LocationServices.API).addConnectionCallbacks(this).build();
			apiClient.connect();
		} else if (apiAvailability.isUserResolvableError(isAvailable)) {
			Dialog dialog = apiAvailability.getErrorDialog(activity, isAvailable, 0);
			dialog.show();
		} else {
			Toast.makeText(activity, "Can't get location data", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * Will stop the location updates
	 */
	public void stopLocationUpdates() {
		if (apiClient != null) {
			LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
		}
	}
	
	@Override
	public void onConnected(@Nullable Bundle bundle) {
		LocationRequest locationRequest = LocationRequest.create();
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		locationRequest.setInterval(50000);
		
		try {
			LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, this);
		} catch (SecurityException ex) {
			Log.e(getClass().getSimpleName(), "SecurityException: No permission for location updates");
		}
	}
	
	@Override
	public void onConnectionSuspended(int i) {
		
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (location == null) {
			Log.e(getClass().getSimpleName(), "No location available");
		} else {
			
			try {
				Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
				String city = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getAddressLine(0);
				String latitude = Double.toString(location.getLatitude());
				String longitude = Double.toString(location.getLongitude());

				// TODO: Handle location data
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
