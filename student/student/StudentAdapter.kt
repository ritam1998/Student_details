package com.qi.student.student

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.qi.student.R
import com.qi.student.student.StudentAdapter.*

class StudentAdapter(val studentModelList: ArrayList<StudentModel>, val studentSelectedCallback: StudentSelectedCallback)
    : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates.*/
        holder.bindStudentList(studentModelList[position])
    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.StudentViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listview_studentname, parent, false)
        return StudentViewHolder(v, studentSelectedCallback)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {

        Log.d("hello","checking purpose ")
        Log.v(ContentValues.TAG, "student list size=" + studentModelList.size)
        return studentModelList.size
    }

    class StudentViewHolder(val itView : View, val studentSelectedCallback: StudentSelectedCallback) : RecyclerView.ViewHolder(itView) {

        var studentId = itView.findViewById(R.id.studentId) as TextView
        var username  = itView.findViewById(R.id.Username) as TextView
        var address  = itView.findViewById(R.id.ViewAdress) as TextView

        fun bindStudentList(studentModel : StudentModel){

            studentId.text = studentModel.studentId.toString()
            username.text = studentModel.username
            address.text = studentModel.address

            itView?.setOnClickListener{
                Toast.makeText(itView.context,username.text,Toast.LENGTH_LONG).show()
                //itView.context.startActivity(Intent(itView.context,UpdateDetail :: class.java))
//                startActivityForResult( ,Intent(itView.context,UpdateDetail :: class.java),1)
                studentSelectedCallback.onStudentClicked(studentModel)
            }
        }

    }
    interface StudentSelectedCallback{
        fun onStudentClicked(studentModel: StudentModel)
    }
}
