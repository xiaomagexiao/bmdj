package com.joke.bamenshenqi.util;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joke.bamenshenqi.db.greendao.DaoMaster;
import com.joke.bamenshenqi.db.greendao.DaoMaster.OpenHelper;
import com.joke.bamenshenqi.db.greendao.DaoSession;
import com.joke.bamenshenqi.db.greendao.NoteDao;
import com.joke.bamenshenqi.db.greendao.NoteDao.Properties;
import com.joke.bamenshenqi.db.greendao.ReviseDao;
import com.joke.bamenshenqi.db.greendao.StudentDao;

public class UpDataBase {
	public static final String TAG = "DaoExample";
	public static Cursor cursor;
	public static DaoMaster daoMaster;
	public static DaoSession daoSession;
	public static SQLiteDatabase db;

	public UpDataBase() {
		super();
	}

	private void addNote() {
		UpDataBase.getStudentDao().insertInTx(new ArrayList());
		UpDataBase.getStudentDao().queryBuilder()
			.where(Properties.Id.eq("1")).build().list();
		//先注释掉  可能报错
//		UpDataBase.getStudentDao().queryBuilder()
//		.where(Properties.Id.eq("1"), new WhereCondition[] { Properties.Comment.eq("dsakl") }).build().list();
		UpDataBase.cursor.requery();
	}

	public static NoteDao getNoteDao() {
		return UpDataBase.daoSession.getNoteDao();
	}

	public static ReviseDao getReviseDao() {
		return UpDataBase.daoSession.getReviseDao();
	}

	public static StudentDao getStudentDao() {
		return UpDataBase.daoSession.getStudentDao();
	}

	public static void setupDatabase(Activity activity) {
		UpDataBase.db = new OpenHelper(((Context) activity), "notes-db", null).getWritableDatabase();
		UpDataBase.daoMaster = new DaoMaster(UpDataBase.db);
		UpDataBase.daoSession = UpDataBase.daoMaster.newSession();
	}
}
