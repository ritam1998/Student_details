package com.qi.student.Subject

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qi.student.R

class AddSubjectActivity : AppCompatActivity(){

    private var addsubject : EditText ?= null
    private var addsubjectButton : Button?= null
    private var addsubjectToString : String ?= null

    var insertsubject =  InsertSubject(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_subject)

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowHomeEnabled(true)


        insertsubject.open()

        addsubject =  findViewById(R.id.addsubjecttext)

        addsubjectButton = findViewById(R.id.savesubjectbutton)

        addsubjectButton?.setOnClickListener{
            saveSubjectdetail()
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun saveSubjectdetail(){

        addsubjectToString = addsubject?.text?.toString()

        if(addsubjectToString.equals("")){

            Toast.makeText(this,"Please Fill Up Subject",Toast.LENGTH_LONG).show()
        }else{
            var status =  insertsubject.insertsubject(this,addsubjectToString)
        }
    }
}