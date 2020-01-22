package com.qi.student.student

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.qi.student.DataBaseHandle
import java.lang.Exception
import java.sql.SQLException

class InsertStudentDetail(context: Context){


    internal  var ok = "OK"

    /* method returns an Instance of the database*/
    val studentdatabaseInstance : SQLiteDatabase
        get() = db

    init {
        studentDBHandler = DataBaseHandle(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        )
    }

    /* Method to open tha database*/

    @Throws(SQLException :: class)
    fun open() : InsertStudentDetail{
        db = studentDBHandler.writableDatabase
        return this
    }
    fun insertStudentData(context: Context,username : String?,email : String?,phone : String?,pincode : String?,classId : Int?,address : String?,password : String?){

        try {

            var addnewvalues = ContentValues()

            addnewvalues.put("username",username)
            addnewvalues.put("email",email)
            addnewvalues.put("phone",phone)
            addnewvalues.put("pincode",pincode)
            addnewvalues.put("class",classId)
            addnewvalues.put("address",address)
            addnewvalues.put("password",password)

            val result = db.insert("student_details",null,addnewvalues)

            Log.v("insert Student","student details"+result)

            Toast.makeText(context,"Student Info Saved",Toast.LENGTH_LONG).show()
        }catch (error : Exception){

        }
    }
    fun fetchStudentDetails(context: Context) : ArrayList<StudentModel>{

        var Studentdetail = "select * from student_details"

        var db = studentDBHandler.readableDatabase

        var studentCursor = db.rawQuery(Studentdetail,null)

        var studentlist = ArrayList<StudentModel>()


        if(studentCursor.count == 0){
            Toast.makeText(context,"No Student Details Found",Toast.LENGTH_LONG).show()
        } else {
            while(studentCursor.moveToNext()){

                var student_details = StudentModel()

                student_details.studentId = studentCursor.getInt(studentCursor.getColumnIndex("student_id"))
                student_details.username = studentCursor.getString(studentCursor.getColumnIndex(USERNAME))
                student_details.address = studentCursor.getString(studentCursor.getColumnIndex(ADDRESS))
                student_details.email = studentCursor.getString(studentCursor.getColumnIndex(EMAIL))
                student_details.phone = studentCursor.getString(studentCursor.getColumnIndex(PHONE))

                studentlist.add(student_details)
            }
            Toast.makeText(context,"All student details found",Toast.LENGTH_LONG).show()
        }
        Log.v("student data","Student ==== $studentlist")
        studentCursor.close()
        db.close()
        return studentlist
    }

    fun updatestudentdetails(context: Context?,studentId : Int,username : String?,email : String?,phone : String?,address : String?){

        try{
            var studentmodel = StudentModel()

            var updatedetail = ContentValues()
            updatedetail.put("username",username)
            updatedetail.put("email",email)
            updatedetail.put("phone",phone)
            updatedetail.put("address",address)

            val sucess_update =  db.update("student_details",updatedetail,"student_id = ?", arrayOf(studentId.toString()))
        }catch (error : Exception){

        }
    }

    companion object{
        internal  val DATABASE_NAME = "StudentDatabase.db"
        internal val DATABASE_VERSION = 1
        var USERNAME = "username"
        var EMAIL = "email"
        var PHONE = "phone"
        var PINCODE = "pincode"
        var CLASSID = "class"
        var ADDRESS = "address"
        var PASSWORD = "password"

        internal val DATABASE_CREATE_STUDENT_DETAILS =
            "create table student_details(student_id integer primary key autoincrement,$USERNAME text,$EMAIL text,$PHONE text,$PINCODE text,$CLASSID integer,$ADDRESS text,$PASSWORD text)"

        lateinit var db : SQLiteDatabase

        lateinit var studentDBHandler : DataBaseHandle

    }
}

