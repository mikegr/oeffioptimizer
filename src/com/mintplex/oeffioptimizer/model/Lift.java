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
 * Created by mike on 04.09.13.
 */
@DatabaseTable(tableName="lifts")
public class Lift {

    public Lift() {
        super();
    }

    @DatabaseField(columnName="FK_STEIG_ID")
    public long fkSteigId;
    
    @DatabaseField(columnName="SYMBOLS")
    public String symbols;
    
    @DatabaseField(columnName="HINT")
    public String hint;
}
