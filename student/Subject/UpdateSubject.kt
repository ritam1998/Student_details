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
import com.qi.student.student.StudentModel

class UpdateSubject : AppCompatActivity(){

    private var subjectId : Int = 0
    private var updatesubjectButton : Button?= null
    private var updatetext : EditText ?= null

    var insertsubject =  InsertSubject(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_subject)

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowHomeEnabled(true)

        if(intent.hasExtra("SubjectModel")){
            val subjectModel = intent.getParcelableExtra<SubjectModel>("SubjectModel")
            subjectId = subjectModel?.subjectid?:0
            Log.v("subject Id","Id === "+subjectModel.subjectid)
            showsubject(subjectModel)
        }

        updatesubjectButton = findViewById(R.id.updatesubjectbutton)

        updatetext = findViewById(R.id.subjectupdate)

        insertsubject.open()

        updatesubjectButton?.setOnClickListener{
            updateSubject()
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
    fun updateSubject(){

        var updatesubjectTostring : String ?= null

        updatesubjectTostring = updatetext?.text?.toString()

        if(updatesubjectTostring.equals("")){
            Toast.makeText(this,"Fill Up",Toast.LENGTH_LONG).show()
        } else {

            var status = insertsubject.updateSubjectData(this,subjectId = subjectId,subjectname = updatesubjectTostring)
        }
    }
    fun showsubject(subjectModel: SubjectModel){

        updatetext?.setText(subjectModel.subjectname)
    }
}