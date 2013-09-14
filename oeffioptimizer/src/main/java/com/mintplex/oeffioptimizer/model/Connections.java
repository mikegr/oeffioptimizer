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

/**
 * Created by mike on 03.09.13.
 */
public class Connections extends SugarRecord<Connections> {



    public long id;
    public long fkSteigId;
    public Steige transferId;
    public Exit fkExit;
    public String symbols;
    public String hint;

    public Connections(Context context) {
        super(context);
    }
}
