package com.qi.student.Subject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.qi.student.DataBaseHandle
import java.lang.Exception

class InsertSubject(context: Context) {

    var ok = "OK"

    val subjectInstance : SQLiteDatabase
        get() = db

    init {
        subjectdbHelper = DataBaseHandle(context, DATABASE_NAME,null, DATABASE_VERSION)
    }

    fun open() : InsertSubject{

        db = subjectdbHelper.writableDatabase
        return this
    }

    fun insertsubject(context: Context,subjectname : String?) : String{

        try{
            var subjectvalues =  ContentValues()

            subjectvalues.put("subjectname",subjectname)

            val result = db.insert("subject_details",null,subjectvalues)

            Log.v(ContentValues.TAG,"result ==> "+result)
            Toast.makeText(context, "Subject Name Saved", Toast.LENGTH_LONG).show()

        }catch (error : Exception){
            Log.v("Insert Subject","Exception === "+error)
        }
        return ok
    }
    fun fetchsubjectdetail(context: Context) : ArrayList<SubjectModel>{

        var fetchResult = "select * from subject_details"

        var db =  subjectdbHelper.readableDatabase

        var subjectcursor = db.rawQuery(fetchResult,null)

        var subjectList = ArrayList<SubjectModel>()



        if(subjectcursor.count == 0){
            Toast.makeText(context,"No Subject Record Found !",Toast.LENGTH_LONG).show()
        } else {
            while(subjectcursor.moveToNext()){

                var subjectdetail = SubjectModel()
//                var allsubject = SubjectModel(subjectcursor.getString(subjectcursor.getColumnIndex(SUBJECT_NAME)))
//                subjectList.add(allsubject)

                subjectdetail.subjectid = subjectcursor.getInt(subjectcursor.getColumnIndex("subjectid"))
                subjectdetail.subjectname = subjectcursor.getString(subjectcursor.getColumnIndex(SUBJECT_NAME))

                subjectList.add(subjectdetail)
            }
            Toast.makeText(context,"ALL SUBJECT RECORD FOUND",Toast.LENGTH_LONG).show()
        }

        subjectcursor.close()
        db.close()
        return subjectList
    }
    fun updateSubjectData(context: Context,subjectId : Int,subjectname : String?){

        try {

            var subjectModel =  SubjectModel()

            var updateSubjectdetail = ContentValues()

            updateSubjectdetail.put("subjectname",subjectname)

            val sucess_update =  db.update("subject_details",updateSubjectdetail,"subjectid = ?", arrayOf(subjectId.toString()))

            Log.v("update successfully","data ==="+sucess_update)
        }catch (ex : Exception){
            Log.v("update Subject","error === >"+ex)
        }
    }
    companion object{

        private val DATABASE_NAME = "StudentDatabase.db"
        private val DATABASE_VERSION = 1

        var SUBJECT_NAME = "subjectname"

        internal  val CREATE_SUBJECT_TABLE = "create table subject_details(subjectid integer primary key autoincrement,$SUBJECT_NAME text)"

        lateinit var subjectdbHelper : DataBaseHandle

        lateinit var  db :  SQLiteDatabase
    }
}