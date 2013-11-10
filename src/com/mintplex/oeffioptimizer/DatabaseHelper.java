package com.mintplex.oeffioptimizer;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLDataException;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Exitinfo;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.model.Lift;
import com.mintplex.oeffioptimizer.model.Steige;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, App.DB_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	Dao<Haltestellen, Integer> haltestellenDao;
	
	public Dao<Haltestellen, Integer> getHaltestellenDao() throws SQLException {
		if (haltestellenDao == null) {
			haltestellenDao = (Dao<Haltestellen, Integer>) getDao(Haltestellen.class);
		}
		return haltestellenDao;
	}

	Dao<Steige, Integer> steigeDao;
	
	public Dao<Steige, Integer> getSteigeDao() throws SQLException {
		if (steigeDao == null) {
			steigeDao = (Dao<Steige, Integer>) getDao(Steige.class);
		}
		return steigeDao;
	}
	
	Dao<Connections, Integer> connectionsDao;
	
	public Dao<Connections, Integer> getConnectionsDao() throws SQLException {
		if (connectionsDao == null) {
			connectionsDao = (Dao<Connections, Integer>) getDao(Connections.class);
		}
		return connectionsDao;
	}
	
	Dao<Exitinfo, Integer> exitinfoDao;
	public Dao<Exitinfo, Integer> getExitinfoDao() throws SQLException {
		if (exitinfoDao == null) {
			exitinfoDao = (Dao<Exitinfo, Integer>) getDao(Exitinfo.class);
		}
		return exitinfoDao;
	}
	
	Dao<Lift, Integer> liftDao;
	public Dao<Lift, Integer> getLiftDao() throws SQLException {
		if (liftDao == null) {
			liftDao = (Dao<Lift, Integer>) getDao(Lift.class);
		}
		return liftDao;
	}
	
	
}
