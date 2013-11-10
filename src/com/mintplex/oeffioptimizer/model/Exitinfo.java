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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="exitinfos")
public class Exitinfo {

	public Exitinfo() {
		super();
	}
	
//	@DatabaseField(columnName="id")
//	public long id;
	
	@DatabaseField(columnName="FK_STEIG_ID")
	public long fkSteigId;
	
	@DatabaseField(columnName="FK_EXIT_ID", foreign=true, foreignAutoRefresh=true)
	public Exit fkExitId;
	@DatabaseField(columnName="SYMBOLS")
	public String symbols;
	@DatabaseField(columnName="HINT")
	public String hint;
}
