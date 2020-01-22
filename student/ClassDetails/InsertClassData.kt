package com.qi.student.ClassDetails

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.qi.student.DataBaseHandle

class InsertClassData(// Context of the application using the database.
    private val context: Context
) {
    internal var ok = "OK"
    // method returns an Instance of the Database

    val databaseInstance: SQLiteDatabase
        get() = db

    init {
        dbHelper = DataBaseHandle(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        )
    }

    // Method to openthe Database
    @Throws(SQLException::class)
    fun open(): InsertClassData {
        db = dbHelper.writableDatabase
        return this
    }

    // Method to close the Database

    // method to insert a record in Table
    fun insertClassEntry(classname: String?): String {
        try {
            val newValues = ContentValues()

            // Assign values for each column.
            newValues.put("class_name", classname)

            // Insert the row into your table
            //db = dbHelper.writableDatabase
            val result = db.insert("class_Table", null, newValues)
            Log.v(ContentValues.TAG,"result ==> "+result)
            Toast.makeText(context, "ClassModel Info Saved", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            println("Exceptions $ex")
            Log.e("Note", "One row entered")
        }

        return ok
    }
    fun fetchData(context: Context) : ArrayList<ClassModel>{

        var fetchAllData = "select * from class_Table"

        var db = dbHelper.readableDatabase

        var  cursor = db.rawQuery(fetchAllData,null)


        var allclasslist = ArrayList<ClassModel>()

        if(cursor.count == 0){
            Toast.makeText(context,"No Record Found",Toast.LENGTH_LONG).show()
        } else {
            while(cursor.moveToNext()){

                var allclassname = ClassModel(cursor.getString(cursor.getColumnIndex(CLASS_NAME)))
                allclasslist.add(allclassname)
            }
            Toast.makeText(context,"All Records Found",Toast.LENGTH_LONG).show()
        }
        Log.v("check","list ===== $allclasslist")
        cursor.close()
        db.close()
        return allclasslist
    }

    companion object {

        internal val DATABASE_NAME = "StudentDatabase.db"
        internal val DATABASE_VERSION = 1
        var getPassword = ""
        val NAME_COLUMN = 1
        var CLASS_NAME = "class_name"
        // TODO: Create public field for each column in your table.
        // SQL Statement to create a new database.

        internal val DATABASE_CREATE_ClASS =
            "create table class_Table( class_id integer primary key autoincrement,$CLASS_NAME  text); "

        // Variable to hold the database instance
        lateinit var db: SQLiteDatabase

        // Database open/upgrade helper
        private lateinit var dbHelper: DataBaseHandle
    }

}
