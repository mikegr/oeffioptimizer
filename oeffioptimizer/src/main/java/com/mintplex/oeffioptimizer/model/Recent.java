package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

public class Recent extends SugarRecord<Recent> {

	public Haltestellen fkHaltestellenId;
	public long lastAccess;

	public Recent(Context arg0) {
		super(arg0);
	}
	
	@Override
	public String toString() {
		if (fkHaltestellenId != null) {
			return fkHaltestellenId.name;
		}
		return "";
	}
	
}
