package com.qi.student.student

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qi.student.ClassDetails.StudentClass
import com.qi.student.R

class UpdateDetail : AppCompatActivity(){

    private var usernameupdate : EditText ?= null
    private var emailupdate : EditText ?= null
    private var addressupdate : EditText ?= null
    private var phoneupdate :  EditText ?= null
    private var update : Button?= null
    private var studentid : Int = 0

    companion object{
        lateinit var  updatedetail : InsertStudentDetail
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_student_data)

        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayShowHomeEnabled(true)

        update = findViewById(R.id.updatebutton)

        update?.setOnClickListener{
            updatedetails()
            finish()
        }

        usernameupdate = findViewById(R.id.updateusername)
        emailupdate = findViewById(R.id.updateemail)
        addressupdate = findViewById(R.id.updateaddress)
        phoneupdate = findViewById(R.id.updatephone)

        if (intent.hasExtra("StudentModel")) {
            val studentModel = intent.getParcelableExtra<StudentModel>("StudentModel")
            Log.v("student Id","data ==="+studentModel.studentId)
            studentid = studentModel?.studentId?:0

            showtext(studentModel)
        }



       updatedetail = InsertStudentDetail(this)

        //Log.v("check student Id ","student id ${studentId.studentId}")

        updatedetail.open()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun updatedetails(){

        var usernameupdateTostring = usernameupdate?.text?.toString()
        var emailupdateTostring = emailupdate?.text?.toString()
        var addressupdateTostring = addressupdate?.text?.toString()
        var phoneupdateTostring = phoneupdate?.text?.toString()

        if(usernameupdateTostring.equals("") || emailupdateTostring.equals("") || addressupdateTostring.equals("") || phoneupdateTostring.equals("")) {
            Toast.makeText(this,"Fill Details",Toast.LENGTH_LONG).show()
        } else {
            val updateStatus = updatedetail.updatestudentdetails(this,studentId = studentid,username = usernameupdateTostring,email = emailupdateTostring,phone = phoneupdateTostring,address = addressupdateTostring)
        }
    }
    fun showtext(studentModel: StudentModel){

        usernameupdate?.setText(studentModel.username)
        addressupdate?.setText(studentModel.address)
        emailupdate?.setText(studentModel.email)
        phoneupdate?.setText(studentModel.phone)
    }
}