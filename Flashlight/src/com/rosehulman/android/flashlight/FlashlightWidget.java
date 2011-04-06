package com.rosehulman.android.flashlight;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class FlashlightWidget extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		
		// Perform this loop procedure for each App Widget that belongs to this provider
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			
			// Create an Intent to launch ExampleActivity
			Intent settingsIntent = new Intent(context, FlashlightPreferences.class);
			Intent appIntent = new Intent(context, Flashlight.class);
			PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent, 0);
			PendingIntent pendingAppIntent = PendingIntent.getActivity(context, 0, appIntent, 0);
			
			// Get the layout for the App Widget and attach an on-click listener to the button
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
			views.setOnClickPendingIntent(R.id.app, pendingAppIntent);
			views.setOnClickPendingIntent(R.id.settings, pendingSettingsIntent);
			
			// Tell the AppWidgetManager to perform an update on the current App Widget
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
}
