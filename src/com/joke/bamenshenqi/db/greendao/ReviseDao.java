package com.joke.bamenshenqi.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class ReviseDao extends AbstractDao {
	public static class Properties {
		public static Property Id;
		public static Property Name;

		static {
			Properties.Id = new Property(0, Long.class, "id", true, "_id");
			Properties.Name = new Property(1, String.class, "name", false, "NAME");
		}

		public Properties() {
			super();
		}
	}

	public static final String TABLENAME = "REVISE";

	public ReviseDao(DaoConfig config, DaoSession daoSession) {
		super(config, ((AbstractDaoSession) daoSession));
	}

	public ReviseDao(DaoConfig config) {
		super(config);
	}

	protected void bindValues(SQLiteStatement stmt, Revise entity) {
		stmt.clearBindings();
		Long v0 = entity.getId();
		if (v0 != null) {
			stmt.bindLong(1, v0.longValue());
		}

		stmt.bindString(2, entity.getName());
	}

	protected void bindValues(SQLiteStatement arg1, Object arg2) {
		this.bindValues(arg1, ((Revise) arg2));
	}

	public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
		String v0 = ifNotExists ? "IF NOT EXISTS " : "";
		db.execSQL("CREATE TABLE " + v0 + "\'REVISE\' (" + "\'_id\' INTEGER PRIMARY KEY ," + "\'NAME\' TEXT NOT NULL );");
	}

	public static void dropTable(SQLiteDatabase db, boolean ifExists) {
		StringBuilder v2 = new StringBuilder().append("DROP TABLE ");
		String v1 = ifExists ? "IF EXISTS " : "";
		db.execSQL(v2.append(v1).append("\'REVISE\'").toString());
	}

	public Long getKey(Revise entity) {
		Long v0 = entity != null ? entity.getId() : null;
		return v0;
	}

	public Object getKey(Object arg2) {
		return this.getKey(((Revise) arg2));
	}

	protected boolean isEntityUpdateable() {
		return true;
	}

	public Revise readEntity(Cursor cursor, int offset) {
		Long v1 = cursor.isNull(offset) ? null : Long.valueOf(cursor.getLong(offset));
		return new Revise(v1, cursor.getString(offset + 1));
	}

	public void readEntity(Cursor cursor, Revise entity, int offset) {
		Long v0 = cursor.isNull(offset) ? null : Long.valueOf(cursor.getLong(offset));
		entity.setId(v0);
		entity.setName(cursor.getString(offset + 1));
	}

	public void readEntity(Cursor arg1, Object arg2, int arg3) {
		this.readEntity(arg1, ((Revise) arg2), arg3);
	}

	public Long readKey(Cursor cursor, int offset) {
		Long v0 = cursor.isNull(offset) ? null : Long.valueOf(cursor.getLong(offset));
		return v0;
	}

	protected Long updateKeyAfterInsert(Revise entity, long rowId) {
		entity.setId(Long.valueOf(rowId));
		return Long.valueOf(rowId);
	}

	protected Object updateKeyAfterInsert(Object arg3, long arg4) {
		return this.updateKeyAfterInsert(((Revise) arg3), arg4);
	}
}
