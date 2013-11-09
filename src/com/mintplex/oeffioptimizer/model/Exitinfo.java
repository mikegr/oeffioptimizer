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

public class Exitinfo extends SugarRecord<Exitinfo> {

	
	public Exitinfo(Context arg0) {
		super(arg0);
	}
	
	public long id;
	public Exit fkExitId;
	public String symbols;
	public String hint;
}
