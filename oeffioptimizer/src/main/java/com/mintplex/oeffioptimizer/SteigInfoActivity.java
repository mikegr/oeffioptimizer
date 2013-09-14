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
