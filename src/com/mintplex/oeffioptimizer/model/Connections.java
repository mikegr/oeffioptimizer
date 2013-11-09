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
@DatabaseTable(tableName="co")
public class Connections {

	@DatabaseField(columnName="connection_id", id=true)
    public long id;
	@DatabaseField(columnName="fk_steig_id")
    public long fkSteigId;
	@DatabaseField(columnName="transfer_id", foreign=true)
    public Steige transferId;
	
    @DatabaseField(columnName="symbols")
    public String symbols;
    @DatabaseField(columnName="hint")
    public String hint;

    public Connections() {
    }

    public boolean isFront(){
    	return symbols.contains("V");
    }
    public boolean isMiddle() {
    	return symbols.contains("M");
    }
    public boolean isBack() {
    	return symbols.contains("H");
    }
}
