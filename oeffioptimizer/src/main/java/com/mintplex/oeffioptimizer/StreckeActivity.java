package com.mintplex.oeffioptimizer;

import android.app.Activity;
import android.content.Intent;

public class StreckeActivity {

	public static void start(Activity ctx, long id) {
		Intent i = new Intent(ctx, StreckeActivity.class);
		i.putExtra("id", id);
		ctx.startActivity(i);
	}
}
