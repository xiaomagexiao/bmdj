package com.joke.bamenshenqi.db.greendao;

import java.util.Date;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class NoteDao extends AbstractDao {
	public static class Properties {
		public static Property Comment;
		public static Property Date;
		public static Property Id;
		public static Property Text;

		static {
			Properties.Id = new Property(0, Long.class, "id", true, "_id");
			Properties.Text = new Property(1, String.class, "text", false, "TEXT");
			Properties.Comment = new Property(2, String.class, "comment", false, "COMMENT");
			Properties.Date = new Property(3, Date.class, "date", false, "DATE");
		}

		public Properties() {
			super();
		}
	}

	public static final String TABLENAME = "NOTE";

	public NoteDao(DaoConfig config, DaoSession daoSession) {
		super(config, ((AbstractDaoSession) daoSession));
	}

	public NoteDao(DaoConfig config) {
		super(config);
	}

	protected void bindValues(SQLiteStatement stmt, Note entity) {
		stmt.clearBindings();
		Long v2 = entity.getId();
		if (v2 != null) {
			stmt.bindLong(1, v2.longValue());
		}

		stmt.bindString(2, entity.getText());
		String v0 = entity.getComment();
		if (v0 != null) {
			stmt.bindString(3, v0);
		}

		Date v1 = entity.getDate();
		if (v1 != null) {
			stmt.bindLong(4, v1.getTime());
		}
	}

	protected void bindValues(SQLiteStatement arg1, Object arg2) {
		this.bindValues(arg1, ((Note) arg2));
	}

	public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
		String v0 = ifNotExists ? "IF NOT EXISTS " : "";
		db.execSQL("CREATE TABLE " + v0 + "\'NOTE\' (" + "\'_id\' INTEGER PRIMARY KEY ," + "\'TEXT\' TEXT NOT NULL ,"
				+ "\'COMMENT\' TEXT," + "\'DATE\' INTEGER);");
	}

	public static void dropTable(SQLiteDatabase db, boolean ifExists) {
		StringBuilder v2 = new StringBuilder().append("DROP TABLE ");
		String v1 = ifExists ? "IF EXISTS " : "";
		db.execSQL(v2.append(v1).append("\'NOTE\'").toString());
	}

	public Long getKey(Note entity) {
		Long v0 = entity != null ? entity.getId() : null;
		return v0;
	}

	public Object getKey(Object arg2) {
		return this.getKey(((Note) arg2));
	}

	protected boolean isEntityUpdateable() {
		return true;
	}

	public Note readEntity(Cursor cursor, int offset) {
		Date v2 = null;
		Long v1 = cursor.isNull(offset) ? 0 : Long.valueOf(cursor.getLong(offset));
		String v4 = cursor.getString(offset + 1);
		String v3 = cursor.isNull(offset + 2) ? "" : cursor.getString(offset + 2);
		if (!cursor.isNull(offset + 3)) {
			v2 = new Date(cursor.getLong(offset + 3));
		}

		return new Note(v1, v4, v3, v2);
	}

	public void readEntity(Cursor cursor, Note entity, int offset) {
		Date v1 = null;
		Long v0 = cursor.isNull(offset) ? 0 : Long.valueOf(cursor.getLong(offset));
		entity.setId(v0);
		entity.setText(cursor.getString(offset + 1));
		String v0_1 = cursor.isNull(offset + 2) ? "" : cursor.getString(offset + 2);
		entity.setComment(v0_1);
		if (!cursor.isNull(offset + 3)) {
			v1 = new Date(cursor.getLong(offset + 3));
		}

		entity.setDate(v1);
	}

	public void readEntity(Cursor arg1, Object arg2, int arg3) {
		this.readEntity(arg1, ((Note) arg2), arg3);
	}

	public Long readKey(Cursor cursor, int offset) {
		Long v0 = cursor.isNull(offset) ? null : Long.valueOf(cursor.getLong(offset));
		return v0;
	}

	protected Long updateKeyAfterInsert(Note entity, long rowId) {
		entity.setId(Long.valueOf(rowId));
		return Long.valueOf(rowId);
	}

	protected Object updateKeyAfterInsert(Object arg3, long arg4) {
		return this.updateKeyAfterInsert(((Note) arg3), arg4);
	}
}
