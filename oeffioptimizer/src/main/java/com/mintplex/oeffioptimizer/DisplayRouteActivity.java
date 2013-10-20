package com.mintplex.oeffioptimizer;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.mintplex.oeffioptimizer.routing.Trip;

@EActivity(R.layout.activity_default)
public class DisplayRouteActivity extends AbstractActivity {

	@AfterViews
	public void afterViews() {
		setFragment(DisplayRouteFragment.create(trip), DisplayRouteFragment.TAG);
	}

	public static void start(FragmentActivity ctx, Trip trip) {
		Intent i = new Intent(ctx, DisplayRouteActivity_.class);
		i.putExtra("trip", trip);
		ctx.startActivity(i);
	}
	
	@Extra
	Trip trip;
	
}
