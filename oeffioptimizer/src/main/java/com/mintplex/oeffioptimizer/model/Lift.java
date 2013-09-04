package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by mike on 04.09.13.
 */
public class Lift extends SugarRecord<Lift> {

    public Lift(Context context) {
        super(context);
    }

    String symbols;

}
