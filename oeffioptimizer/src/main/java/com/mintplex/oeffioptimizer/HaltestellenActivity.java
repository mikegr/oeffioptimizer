package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Haltestellen;

import android.*;
import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

/**
 * Created by mike on 03.09.13.
 */
public class HaltestellenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_haltestellen);
        
        getSupportActionBar().setTitle(Haltestellen.findById(Haltestellen.class, id).name);
        
        if (savedInstanceState != null) return;
        
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, HaltestellenFragment.create(id));
        ft.commit();
    }
    
    private long id = 214460762;
    
    
    public void start(Activity act, long id) {
    	Intent i = new Intent(act, HaltestellenActivity.class);
    	i.putExtra("id", id);
    	act.startActivity(i);
    }
}
