package com.qi.student.student

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.qi.student.R
import android.view.View
import android.R.attr.country
import android.util.Log
import com.qi.student.ClassDetails.ClassNameAdapter
import com.qi.student.ClassDetails.InsertClassData
import com.qi.student.ClassDetails.StudentClass
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.*


class AddStudentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private var username : EditText ?= null
    private var email : EditText ?= null
    private var phone : EditText ?= null
    private var pincode : EditText ?= null
    private var address :  EditText ?= null
    private var password : EditText ?= null
    private var registerButton : Button?= null
    private var classposition :  Int ?= null

    lateinit var insertClass : InsertClassData

    var savedetails = InsertStudentDetail(this)

    var usernameToString : String ?= null
    var emailToString : String ?= null
    var phoneToString : String ?= null
    var pincodeToString : String ?= null
    var addressToString : String ?= null
    var passwordToString : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        insertClass = InsertClassData(this)

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowHomeEnabled(true)

       var listofclass = insertClass.fetchData(this)


       Log.v("check","index check list == $listofclass")

        /* Add Spinner Class*/

        val spin = findViewById(R.id.spinnerClass) as Spinner
        spin.setOnItemSelectedListener(this)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listofclass)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Setting the ArrayAdapter data on the Spinner
        spin.adapter = aa

        savedetails.open()

        registerButton = findViewById(R.id.savestudentbutton)

        registerButton?.setOnClickListener{
            registerStudentDetail()
        }

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        pincode = findViewById(R.id.pincode)
        address = findViewById(R.id.address)
        password = findViewById(R.id.password)

    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */
        classposition = position
        Log.v("find onItemSelected ","position is ===="+classposition)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */
    }
    /* For Arrow Button */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun registerStudentDetail(){

       usernameToString = username?.text?.toString()
       emailToString = email?.text?.toString()
       phoneToString = phone?.text?.toString()
       pincodeToString = pincode?.text?.toString()
       addressToString = address?.text?.toString()
        passwordToString = password?.text?.toString()

        if(usernameToString.equals("") || emailToString.equals("") || phoneToString.equals("") ||
            pincodeToString.equals("") || addressToString.equals("") || passwordToString.equals("")){

            Toast.makeText(this,"Please Fill Up",Toast.LENGTH_LONG).show()
        } else {

            var status = savedetails.insertStudentData(this,username = usernameToString,email = emailToString,phone = phoneToString,pincode = pincodeToString,classId = classposition,
            address = addressToString,password = passwordToString)

        }
    }
}