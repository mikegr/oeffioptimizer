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
import android.os.Bundle;
import android.view.Menu;

public class StreckeActivity extends AbstractActivity {

	public static void start(Activity ctx, long id) {
		Intent i = new Intent(ctx, StreckeActivity.class);
		i.putExtra("id", id);
		ctx.startActivity(i);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) return;
        setFragment(StreckeFragment.create(getIntent().getLongExtra("id", -1)), StreckeFragment.TAG);
    }
}
