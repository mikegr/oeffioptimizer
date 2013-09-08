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
		ft.replace(android.R.id.content, f);
		ft.commit();
	}
	
	public static void start(Activity ctx, Class<?> clazz, long id) {
		Intent i = new Intent(ctx, clazz);
		i.putExtra("id", id);
		ctx.startActivity(i);
	}
}
