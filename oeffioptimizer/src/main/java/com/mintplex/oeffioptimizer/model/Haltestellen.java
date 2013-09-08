package com.mintplex.oeffioptimizer.model;

import android.content.Context;

import com.orm.SugarRecord;

public class Haltestellen extends SugarRecord<Haltestellen> {

	
	
	public Haltestellen(Context arg0) {
		super(arg0);
	}
	
	public long id;
	public String name;
	
	
	@Override
	public String toString() {
		return name;
	}
}
