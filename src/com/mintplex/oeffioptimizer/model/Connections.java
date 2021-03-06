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

/**
 * Created by mike on 03.09.13.
 */
@DatabaseTable(tableName="connections")
public class Connections {

	@DatabaseField(columnName="ID", id=true)
    public long id;
	@DatabaseField(columnName="FK_STEIG_ID")
    public long fkSteigId;
	@DatabaseField(columnName="FK_TRANSFER_ID", foreign=true, foreignAutoRefresh=true)
    public Steige transferId;
	
    @DatabaseField(columnName="SYMBOLS")
    public String symbols;
    @DatabaseField(columnName="HINT")
    public String hint;

    public Connections() {
    }

    public boolean isFront(){
    	if (symbols != null) return symbols.contains("V");
    	return false;
    }
    public boolean isMiddle() {
    	if (symbols != null) return symbols.contains("M");
    	return false;
    }
    public boolean isBack() {
    	if (symbols != null) return symbols.contains("H");
    	return false;
    }
}
