package com.qi.student.ClassDetails

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.qi.student.R

class AddClassActivity : AppCompatActivity() {

    private var addClassName : EditText ?= null
    private var saveClassName : Button?= null
    var toolbarAddClass : Toolbar ?= null

    var addClassToString : String ?= null

    var saveClass = InsertClassData(this)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addclass)

        addClassName = findViewById(R.id.addClass)
        saveClassName = findViewById(R.id.savebutton)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)


        saveClassName?.setOnClickListener{
            saveClass()
            finish()
        }
        saveClass.open()
    }
    fun saveClass(){

        addClassToString = addClassName?.text?.toString()

        if(addClassToString.equals("")){
            Toast.makeText(this,"FILL THE CLASS NAME",Toast.LENGTH_LONG).show()
        } else {
            var status = saveClass.insertClassEntry(classname = addClassToString)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}