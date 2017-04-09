package com.joke.bamenshenqi.db.greendao;

import java.util.Map;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

public class DaoSession extends AbstractDaoSession {
	private final NoteDao noteDao;
	private final DaoConfig noteDaoConfig;
	private final ReviseDao reviseDao;
	private final DaoConfig reviseDaoConfig;
	private final StudentDao studentDao;
	private final DaoConfig studentDaoConfig;

	public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map arg5) {
		super(db);
		this.noteDaoConfig = ((DaoConfig) arg5.get(NoteDao.class)).clone();
		this.noteDaoConfig.initIdentityScope(type);
		this.studentDaoConfig = ((DaoConfig) arg5.get(StudentDao.class)).clone();
		this.studentDaoConfig.initIdentityScope(type);
		this.reviseDaoConfig = ((DaoConfig) arg5.get(ReviseDao.class)).clone();
		this.reviseDaoConfig.initIdentityScope(type);
		this.noteDao = new NoteDao(this.noteDaoConfig, this);
		this.studentDao = new StudentDao(this.studentDaoConfig, this);
		this.reviseDao = new ReviseDao(this.reviseDaoConfig, this);
		this.registerDao(Note.class, this.noteDao);
		this.registerDao(Student.class, this.studentDao);
		this.registerDao(Revise.class, this.reviseDao);
	}

	public void clear() {
		this.noteDaoConfig.getIdentityScope().clear();
		this.studentDaoConfig.getIdentityScope().clear();
		this.reviseDaoConfig.getIdentityScope().clear();
	}

	public NoteDao getNoteDao() {
		return this.noteDao;
	}

	public ReviseDao getReviseDao() {
		return this.reviseDao;
	}

	public StudentDao getStudentDao() {
		return this.studentDao;
	}
}
