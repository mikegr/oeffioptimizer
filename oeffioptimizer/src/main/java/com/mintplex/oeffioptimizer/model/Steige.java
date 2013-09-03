package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by mike on 03.09.13.
 */
public class Steige extends SugarRecord<Steige> {

    /*
    public String STEIG_ID;
    public String FK_LINIEN_ID;
    public String FK_HALTESTELLEN_ID;

    public String RICHTUNG;
    public String REIHENFOLGE;
    public String RBL_NUMMER;
    public String BEREICH;
    public String STEIG;
    public String STEIG_WGS84_LAT;
    public String STEIG_WGS84_LON;
    public String STAND;
    */

    public String linienName;
    //public String HALTESTELLEN_NAME;
    public String richtungName;

    public Steige(Context context) {
        super(context);
    }

}
