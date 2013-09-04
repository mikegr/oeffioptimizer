package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by mike on 03.09.13.
 */

public class Exit extends SugarRecord<Exit> {
    public long id;
    public String name;

    public Exit(Context context) {
        super(context);
    }
}
