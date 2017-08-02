package com.company.product.models.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Controller for storing preferences
 *
 * @author Gaston Muijtjens
 * @since 18-03-17
 */

public class PreferencesController {
	
	private static final String PREFERENCES_TAG = "com.company.product";
	
	/**
	 * This method will return the SharedPreferences object from a given context
	 *
	 * @param context The context
	 * @return SharedPreferences object
	 */
	private static SharedPreferences getSharedPreferences(@NonNull Context context) {
		return context.getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE);
	}
	
	/**
	 * This method will return the SharedPreferences Editor object
	 *
	 * @param sharedPreferences The SharedPreferences object from which the editor has to be gotten
	 * @return SharedPreferences.Editor object
	 */
	private static SharedPreferences.Editor getSharedPreferencesEditor(@NonNull SharedPreferences sharedPreferences) {
		return sharedPreferences.edit();
	}
	
	/**
	 * This function will clear all shared settings
	 *
	 * @param context The context of the SharedPreferences
	 */
	public static void clearAll(@NonNull Context context) {
		SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferencesEditor(getSharedPreferences(context));
		sharedPreferencesEditor.clear();
		sharedPreferencesEditor.apply();
	}
	
	/**
	 * This method will store a string into the SharedPreferences
	 *
	 * @param context The context of the SharedPreferences
	 * @param key     The key of the preference
	 * @param value   The value of the preference that has to be set
	 */
	public static void storeString(@NonNull Context context, @NonNull String key, @NonNull String value) {
		SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferencesEditor(getSharedPreferences(context));
		sharedPreferencesEditor.putString(key, value);
		sharedPreferencesEditor.apply();
	}
	
	/**
	 * This method will get a string from the SharedPreferences
	 *
	 * @param context The context of the SharedPreferences
	 * @param key     The key of the preference
	 * @return The value of the preference from the given key
	 */
	public static String getString(@NonNull Context context, @NonNull String key) {
		SharedPreferences sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getString(key, null);
	}

}
