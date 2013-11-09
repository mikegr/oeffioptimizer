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
import android.os.Bundle;
import android.view.Menu;

public class SteigInfoActivity extends AbstractActivity {

	public static void start(Activity ctx, long id) {
		start(ctx, SteigInfoActivity.class, id);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) return;
        setFragment(SteigInfoFragment.create(getIntent().getLongExtra("id", -1)), SteigInfoFragment.TAG);
    }
}
