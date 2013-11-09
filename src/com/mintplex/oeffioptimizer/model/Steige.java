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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.orm.SugarRecord;

/**
 * Created by mike on 03.09.13.
 */
@DatabaseTable(tableName="steige")
public class Steige  {

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
	
	@DatabaseField(id=true, columnName="steig_id")
	public long id;
	
	@DatabaseField(columnName="linien_name")
    public String linienName;
	
	@DatabaseField(columnName="haltestellen_name")
    public String haltestellenName;
	
	@DatabaseField(columnName="richtung_name")
    public String richtungName;
	
    public String richtung;
    public long fkLinienId;
    
    public Haltestellen fkHaltestellenId;
    
    @DatabaseField(columnName="reihenfolge")
    public int reihenfolge;

    public Steige() {
        super();
    }
    
    public long getId() {
    	return id;
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
