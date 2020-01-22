package com.qi.student.student

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.qi.student.R

class StudentActivity : AppCompatActivity() {

    private var regStudent: Button? = null
    private var recyclerviewstudent: RecyclerView? = null

    var studentmodel = StudentModel()

    companion object {
        lateinit var studentdata: InsertStudentDetail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_studentname)

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowHomeEnabled(true)

        regStudent = findViewById(R.id.studentaddbutton)

        recyclerviewstudent = findViewById(R.id.studentrecycleviewer)

        //var studentdetails = ArrayList<StudentModel>()

        //studentdetails.add(StudentModel("1","Ritam de","barrackpore"))
        //studentdetails.add(StudentModel("2","Shubham roy","agarpara"))

        //var studentAdapter = StudentAdapter(studentdetails)
        //recyclerviewstudent?.adapter = studentAdapter

        studentdata = InsertStudentDetail(this)

        regStudent?.setOnClickListener {
            startActivityForResult(Intent(this, AddStudentActivity::class.java),1)
        }

        viewstudentlist()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 || requestCode == 2) {
            viewstudentlist()
            finish()
        }
    }

    fun viewstudentlist() {

        var studentlist = studentdata.fetchStudentDetails(this)
        var studentadapter =
            StudentAdapter(studentlist, object : StudentAdapter.StudentSelectedCallback {
                override fun onStudentClicked(studentModel: StudentModel) {
                    startActivityForResult(Intent(this@StudentActivity, UpdateDetail::class.java).putExtra("StudentModel", studentModel), 2)
                }

            })

        recyclerviewstudent?.adapter = studentadapter
    }

}