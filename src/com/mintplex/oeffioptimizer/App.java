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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Application;

/**
 * Created by mike on 03.09.13.
 */
public class App extends Application {

    public static String DB_NAME = "routing.db";


    @Override
    public void onCreate() {

    Log.setTAG("oeffioptimizer");
    Log.setLogLevel(android.util.Log.VERBOSE);
    
        try {
            copyDataBase();
        }
        catch (IOException ex) {
            Log.d("Copy DB failed", ex);
        }
        super.onCreate();
    }

    private void copyDataBase() throws IOException {

        File db = getDatabasePath(DB_NAME);
        db.delete();
        db.getParentFile().mkdirs();

        //if (db.exists()) return;

        String outFileName = db.getAbsolutePath();


        //Open your local db as the input stream
        InputStream myInput = getAssets().open(DB_NAME);

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(db);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }//end of copyDataBase() method
}
