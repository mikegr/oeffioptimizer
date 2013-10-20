/**
 * ÖffiOptimizer
 * Copyright (C) 2013 Michael Greifeneder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.mintplex.oeffioptimizer;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class AbstractActivity extends ActionBarActivity {

	public void setFragment(Fragment f, String tag) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(android.R.id.content, f, tag);
		ft.commit();
	}
	
	public static void start(Activity ctx, Class<?> clazz, long id) {
		Intent i = new Intent(ctx, clazz);
		i.putExtra("id", id);
		ctx.startActivity(i);
	}
}
