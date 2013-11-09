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

@DatabaseTable
public class Exitinfo {

	public Exitinfo() {
		super();
	}
	
//	@DatabaseField(columnName="id")
//	public long id;
	
	@DatabaseField(columnName="fk_steig_id")
	public long fkSteigId;
	
	@DatabaseField(columnName="fk_exit_id", foreign=true)
	public Exit fkExitId;
	@DatabaseField(columnName="symbols")
	public String symbols;
	@DatabaseField(columnName="hint")
	public String hint;
}
