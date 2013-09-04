package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by mike on 03.09.13.
 */
public class Connections extends SugarRecord<Connections> {



    public long id;
    public long fkSteigId;
    public Steige transferId;
    public Exit fkExit;
    public String symbols;
    public String hint;

    public Connections(Context context) {
        super(context);
    }
}
