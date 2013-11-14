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

public class Recent  {

	public Haltestellen fkHaltestellenId;
	public long lastAccess;

	public Recent() {
	}
	
	@Override
	public String toString() {
		if (fkHaltestellenId != null) {
			return fkHaltestellenId.name;
		}
		return "";
	}
	
}
