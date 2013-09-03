package com.mintplex.oeffioptimizer;

import android.util.Log;

import com.googlecode.androidannotations.annotations.EApplication;
import com.orm.SugarApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mike on 03.09.13.
 */
public class App extends SugarApp {

    private static String DB_NAME = "oeffioptimizer.db";


    @Override
    public void onCreate() {

        try {
            copyDataBase();
        }
        catch (IOException ex) {
            Log.d("", "Copy DB failed", ex);
        }
        super.onCreate();
    }

    private void copyDataBase() throws IOException {

        File db = getDatabasePath(DB_NAME);
        db.mkdirs();

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
