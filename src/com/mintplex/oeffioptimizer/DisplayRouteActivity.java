package com.mintplex.oeffioptimizer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;

import com.google.gson.Gson;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.mintplex.oeffioptimizer.routing.Routing;
import com.mintplex.oeffioptimizer.routing.Trip;

@EActivity(R.layout.activity_default)
public class DisplayRouteActivity extends AbstractActivity {

	@AfterViews
	public void afterViews() {
		ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		setFragment(DisplayRouteFragment.create(trip), DisplayRouteFragment.TAG);
	}

	public static void start(FragmentActivity ctx, Trip trip) {
		Intent i = new Intent(ctx, DisplayRouteActivity_.class);
		i.putExtra("trip", trip);
		ctx.startActivity(i);
	}
	
	@Extra
	Trip trip;

	@AfterInject
	public void afterInject() {
		if (trip == null) {
			try {
				InputStream is = getAssets().open("test_fussweg.json");
				Gson gson = new Gson();
				Routing routing = gson.fromJson(new InputStreamReader(is, Charset.defaultCharset()), Routing.class);
				trip = routing.getTrips().get(0).getTrip();
				
			} catch (Exception e) {
				Log.e("Read trip from assets folder failed", e);
			}
		}
	}
	
}
