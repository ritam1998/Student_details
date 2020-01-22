package com.qi.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.qi.student.ClassDetails.StudentClass
import com.qi.student.Subject.AddSubjectActivity
import com.qi.student.Subject.SubjectRecyclerviewActivity
import com.qi.student.Teacher.TeacherMainActivity
import com.qi.student.student.StudentActivity

class MainActivity : AppCompatActivity() {

    private var studentReg : Button ?= null
    private var classReg : Button ?= null
    private var subject : Button ?= null
    private var teacher : Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentReg = findViewById(R.id.student)

        studentReg?.setOnClickListener{
            startActivity(Intent(this, StudentActivity:: class.java))    // For student Registration
        }

        classReg = findViewById(R.id.classname)

        classReg?.setOnClickListener{
            startActivity(Intent(this, StudentClass:: class.java))
        }

        subject = findViewById(R.id.subject)

        subject?.setOnClickListener{
            startActivity(Intent(this,SubjectRecyclerviewActivity :: class.java))
        }

        teacher = findViewById(R.id.teacher)

        teacher?.setOnClickListener{
            startActivity(Intent(this,TeacherMainActivity :: class.java))
        }
    }
}
