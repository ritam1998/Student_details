package com.qi.student.ClassDetails

import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.qi.student.ClassDetails.ClassNameAdapter.ViewHolder
import com.qi.student.R

/**

 */

class ClassNameAdapter(val classModelList: ArrayList<ClassModel>) : Adapter<ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassNameAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listview_classname, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ClassNameAdapter.ViewHolder, position: Int) {

        Log.v(TAG,"values =====  "+classModelList[position])
        holder.bindItems(classModelList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {

        Log.d("hello","checking purpose ")
        Log.v(TAG, "index=" + classModelList.size)
        return classModelList.size
    }

    //the class is holding the list view
    class ViewHolder(val itView: View) : RecyclerView.ViewHolder(itView) {

        val textViewName = itView.findViewById(R.id.ViewUsername) as TextView
//        val textViewAddress  = itView.findViewById(R.id.ViewAdress) as TextView

        fun bindItems(classModel: ClassModel) {

            textViewName.text = classModel.classname
//            textViewAddress.text = classModel.address
            //Log.v(TAG,"name === >"+textViewName)

            /* here itView is a context and here i fit a onclick listener */

            itView.setOnClickListener{
                Toast.makeText(itView.context,textViewName.text,Toast.LENGTH_LONG).show()
            }

            /* longpress */
        }

    }

}
