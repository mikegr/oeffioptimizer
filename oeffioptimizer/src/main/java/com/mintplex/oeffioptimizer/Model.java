package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Steige;
import com.mintplex.oeffioptimizer.model.Transfer;

import java.util.List;

/**
 * select linien_name, richtung_name
   from Steige s
   where fk_haltestellen_id = "214460762"
   */

public interface Model {

    public List<Steige> getSteige(long haltestellenId);

    public List<Transfer> getTransfers(long steigId);



}
