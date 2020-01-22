package com.qi.student.Teacher

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.qi.student.R

class TeacherMainActivity : AppCompatActivity(){

    private var teacherrecyclerview : RecyclerView ?= null
    private var addteacherbutton : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitvity_recyclerview_teacher)

        addteacherbutton =  findViewById(R.id.teacheraddbutton)

        addteacherbutton?.setOnClickListener{
            startActivity(Intent(this,AddTeacherActivity :: class.java))
        }
    }
}