package com.joke.bamenshenqi.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class StudentDao extends AbstractDao {
	public static class Properties {
		public static Property Score;
		public static Property TypeName;
		public static Property Id;
		public static Property Name;

		static {
			Properties.Id = new Property(0, Long.class, "id", true, "_id");
			Properties.Name = new Property(1, String.class, "name", false, "NAME");
			Properties.Score = new Property(2, String.class, "score", false, "SCORE");
			Properties.TypeName = new Property(3, String.class, "typeName", false, "TYPE_NAME");
		}
	}

	public static final String TABLENAME = "STUDENT";

	public StudentDao(DaoConfig config, DaoSession daoSession) {
		super(config, ((AbstractDaoSession) daoSession));
	}

	public StudentDao(DaoConfig config) {
		super(config);
	}

	protected void bindValues(SQLiteStatement stmt, Student entity) {
		stmt.clearBindings();
		Long v0 = entity.getId();
		if (v0 != null) {
			stmt.bindLong(1, v0.longValue());
		}

		stmt.bindString(2, entity.getName());
		String v1 = entity.getScore();
		if (v1 != null) {
			stmt.bindString(3, v1);
		}

		String v2 = entity.getTypeName();
		if (v2 != null) {
			stmt.bindString(4, v2);
		}
	}

	protected void bindValues(SQLiteStatement arg1, Object arg2) {
		this.bindValues(arg1, ((Student) arg2));
	}

	public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
		String v0 = ifNotExists ? "IF NOT EXISTS " : "";
		db.execSQL("CREATE TABLE " + v0 + "\'STUDENT\' (" + "\'_id\' INTEGER PRIMARY KEY ," + "\'NAME\' TEXT NOT NULL ,"
				+ "\'SCORE\' TEXT," + "\'TYPE_NAME\' TEXT);");
	}

	public static void dropTable(SQLiteDatabase db, boolean ifExists) {
		StringBuilder v2 = new StringBuilder().append("DROP TABLE ");
		String v1 = ifExists ? "IF EXISTS " : "";
		db.execSQL(v2.append(v1).append("\'STUDENT\'").toString());
	}

	public Long getKey(Student entity) {
		Long v0 = entity != null ? entity.getId() : null;
		return v0;
	}

	public Object getKey(Object arg2) {
		return this.getKey(((Student) arg2));
	}

	protected boolean isEntityUpdateable() {
		return true;
	}

	public Student readEntity(Cursor cursor, int offset) {
		String v2 = null;
		Long v1 = cursor.isNull(offset) ? 0 : Long.valueOf(cursor.getLong(offset));
		String v4 = cursor.getString(offset + 1);
		String v3 = cursor.isNull(offset + 2) ? v2 : cursor.getString(offset + 2);
		if (!cursor.isNull(offset + 3)) {
			v2 = cursor.getString(offset + 3);
		}

		return new Student(v1, v4, v3, v2);
	}

	public void readEntity(Cursor cursor, Student entity, int offset) {
		String v1 = null;
		Long v0 = cursor.isNull(offset) ? 0 : Long.valueOf(cursor.getLong(offset));
		entity.setId(v0);
		entity.setName(cursor.getString(offset + 1));
		String v0_1 = cursor.isNull(offset + 2) ? v1 : cursor.getString(offset + 2);
		entity.setScore(v0_1);
		if (!cursor.isNull(offset + 3)) {
			v1 = cursor.getString(offset + 3);
		}

		entity.setTypeName(v1);
	}

	public void readEntity(Cursor arg1, Object arg2, int arg3) {
		this.readEntity(arg1, ((Student) arg2), arg3);
	}

	public Long readKey(Cursor cursor, int offset) {
		Long v0 = cursor.isNull(offset) ? null : Long.valueOf(cursor.getLong(offset));
		return v0;
	}

	protected Long updateKeyAfterInsert(Student entity, long rowId) {
		entity.setId(Long.valueOf(rowId));
		return Long.valueOf(rowId);
	}

	protected Object updateKeyAfterInsert(Object arg3, long arg4) {
		return this.updateKeyAfterInsert(((Student) arg3), arg4);
	}
}
