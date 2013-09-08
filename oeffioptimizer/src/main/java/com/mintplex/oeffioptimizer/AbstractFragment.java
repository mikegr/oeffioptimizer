package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class AbstractFragment extends Fragment {

	protected long id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.id = getArguments().getLong("id"); 
	}
}
