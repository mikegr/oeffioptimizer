package com.mintplex.oeffioptimizer;

import android.*;
import android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

/**
 * Created by mike on 03.09.13.
 */
public class HaltestellenActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_haltestellen);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, HaltestellenFragment.create(214460762));
        ft.commit();
    }
}
