package com.mintplex.oeffioptimizer;

import java.util.List;

import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.model.Recent;
import com.orm.SugarDb;
import com.orm.SugarRecord;

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
public class HaltestellenActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        id = getIntent().getLongExtra("id", 214460762);

        //setContentView(R.layout.activity_haltestellen);
        
        getSupportActionBar().setTitle(Haltestellen.findById(Haltestellen.class, id).name);
        
        if (savedInstanceState != null) return;
        
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, HaltestellenFragment.create(id));
        ft.commit();
        
        List<Recent> recents = Recent.find(Recent.class, "fk_haltestellen_id = ?", Long.toString(id));
        if (recents.size() > 0) {
        	Recent recent = recents.get(0);
        	recent.lastAccess = System.currentTimeMillis();
        	recent.save();
        }
        else {
        	Recent r = new Recent(getApplicationContext());
        	r.lastAccess = System.currentTimeMillis();
        	r.fkHaltestellenId = Haltestellen.findById(Haltestellen.class, id);
        	r.save();
        }
    }
    
    private long id;
    
    
    public static void start(Activity act, long id) {
    	Intent i = new Intent(act, HaltestellenActivity.class);
    	i.putExtra("id", id);
    	act.startActivity(i);
    }
}
