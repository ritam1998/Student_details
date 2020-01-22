package com.qi.student

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.qi.student.ClassDetails.InsertClassData.Companion.DATABASE_CREATE_ClASS
import com.qi.student.Subject.InsertSubject.Companion.CREATE_SUBJECT_TABLE
import com.qi.student.student.InsertStudentDetail.Companion.DATABASE_CREATE_STUDENT_DETAILS
import java.lang.Exception

class DataBaseHandle(context: Context,name : String?,factory: SQLiteDatabase.CursorFactory?,version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */
        try {
            db?.execSQL(DATABASE_CREATE_ClASS)
            db?.execSQL(DATABASE_CREATE_STUDENT_DETAILS)
            db?.execSQL(CREATE_SUBJECT_TABLE)
        }catch (error : Exception){
            Log.e("Error","Something problem in Create Student Table")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */
        db?.execSQL("DROP TABLE IF EXISTS student_details")
        db?.execSQL("DROP TABLE IF EXISTS class_Table")
        db?.execSQL("DROP TABLE IF EXISTS subject_details")
        onCreate(db)
    }

}