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


@DatabaseTable(tableName="haltestellen")
public class Haltestellen {

	
	
	public Haltestellen() {
	}
	
	@DatabaseField(columnName="HALTESTELLEN_ID", id=true)
	public long id;
	
	@DatabaseField(columnName="NAME")
	public String name;
	
	@DatabaseField(columnName="DIVA")
	public String diva;
	
	
	@Override
	public String toString() {
		return name;
	}
}
