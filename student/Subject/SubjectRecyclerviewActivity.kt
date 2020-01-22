package com.qi.student.Subject

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.qi.student.R
import kotlinx.android.synthetic.main.recyclerview_subject.*
import java.util.ArrayList

class SubjectRecyclerviewActivity : AppCompatActivity(){

    private var subjectaddbutton :  Button ?= null
    var subjectrecyclerview : RecyclerView ?= null

    var subjectlistview = InsertSubject(this)

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_subject)

        subjectrecyclerview  = findViewById(R.id.subjectrecycleviewer)

        subjectaddbutton =  findViewById(R.id.subjectaddbutton)

        var action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowHomeEnabled(true)

        subjectaddbutton?.setOnClickListener{
            startActivityForResult(Intent(this,AddSubjectActivity :: class.java),1)
        }

//        var subjectlist = ArrayList<SubjectModel>()
//
//        subjectlist.add(SubjectModel("English"))
//        subjectlist.add(SubjectModel("Math"))
//
//        var subjectadapter =  SubjectAdapter(subjectlist)
//
//        subjectrecyclerview.adapter = subjectadapter

        subjectview()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1){
            subjectview()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun subjectview(){

        var allsubject =  subjectlistview.fetchsubjectdetail(this)
        var subjectadapter = SubjectAdapter(allsubject,object : SubjectAdapter.SubjectCallback{

            override fun onclickSubject(subjectmodel: SubjectModel) {

                /*TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */
                startActivityForResult(Intent(this@SubjectRecyclerviewActivity,UpdateSubject :: class.java).putExtra("SubjectModel",subjectmodel),2)
            }
        })

        subjectrecyclerview?.adapter = subjectadapter
    }
}