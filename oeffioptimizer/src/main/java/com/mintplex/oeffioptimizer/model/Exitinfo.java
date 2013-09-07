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
