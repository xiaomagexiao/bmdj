package com.joke.bamenshenqi.db.greendao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version 1");
            DaoMaster.createAllTables(db, false);
        }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
    }

    public static final int SCHEMA_VERSION = 1;

    public DaoMaster(SQLiteDatabase db) {
        super(db, 1);
        this.registerDaoClass((Class<? extends AbstractDao<?, ?>>) NoteDao.class);
        this.registerDaoClass((Class<? extends AbstractDao<?, ?>>) StudentDao.class);
        this.registerDaoClass((Class<? extends AbstractDao<?, ?>>) ReviseDao.class);
    }

	public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        NoteDao.createTable(db, ifNotExists);
        StudentDao.createTable(db, ifNotExists);
        ReviseDao.createTable(db, ifNotExists);
    }

    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        NoteDao.dropTable(db, ifExists);
        StudentDao.dropTable(db, ifExists);
        ReviseDao.dropTable(db, ifExists);
    }

    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(this.db, type, this.daoConfigMap);
    }

}

