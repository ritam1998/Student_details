package com.qi.student.Teacher

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.qi.student.DataBaseHandle
import java.sql.SQLException

class InsertTeacher (context: Context){

    internal  var ok = "OK"

    /* method returns an Instance of the database*/
    val studentdatabaseInstance : SQLiteDatabase
        get() = db

    init {
        teacherDBHandler = DataBaseHandle(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        )
    }

    /* Method to open tha database*/

    @Throws(SQLException :: class)
    fun open() : InsertTeacher{
        db = teacherDBHandler.writableDatabase
        return this
    }
    companion object{
        internal  val DATABASE_NAME = "StudentDatabase.db"
        internal val DATABASE_VERSION = 1
        var TEACHERNAME = "teachername"
        var SUBJECT_ID = "subjectid"

        internal val DATABASE_CREATE_TEACHER_DETAILS =
            "create table teacher_details(teacher_id integer primary key autoincrement,$TEACHERNAME text,$SUBJECT_ID integer)"

        lateinit var db : SQLiteDatabase

        lateinit var teacherDBHandler : DataBaseHandle

    }
}