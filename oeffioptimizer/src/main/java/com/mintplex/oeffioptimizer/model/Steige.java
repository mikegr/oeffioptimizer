/**
 * ÖffiOptimizer
 * Copyright (C) 2013 Michael Greifeneder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
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
	
	public long id;
	
    public String linienName;
    public String haltestellenName;
    //public String HALTESTELLEN_NAME;
    public String richtungName;
    public String richtung;
    public long fkLinienId;
    public Haltestellen fkHaltestellenId;
    public int reihenfolge;

    public Steige(Context context) {
        super(context);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Steige other = (Steige) obj;
		if (id != other.id)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return fkHaltestellenId.name;
    }
}
