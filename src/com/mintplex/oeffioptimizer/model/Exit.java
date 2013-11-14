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

/**
 * Created by mike on 03.09.13.
 */

@DatabaseTable(tableName="exits")
public class Exit {
	@DatabaseField(columnName="EXIT_ID", id=true)
    public long id;
	@DatabaseField(columnName="EXIT_NAME")
    public String name;


	public Exit() {}

}
