/**
 * ÖffiOptimizer
 * Copyright (C) 2013 Michael Greifeneder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Steige;

import java.util.List;

/**
 * select linien_name, richtung_name
   from Steige s
   where fk_haltestellen_id = "214460762"
   */

public interface Model {

    public List<Steige> getSteige(long haltestellenId);

    public List<Connections> getTransfers(long steigId);



}
