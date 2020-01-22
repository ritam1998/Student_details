package com.qi.student.Subject

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qi.student.R

class SubjectAdapter(var subjectlist : ArrayList<SubjectModel>,val subjectcallback : SubjectCallback) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>(){


    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */

        holder.bindsubjectitem(subjectlist[position])
    }

    override fun getItemCount(): Int {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */

        return subjectlist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {

        /* TODO("not implemented") //To change body of created functions use File | Settings | File Templates. */

        val v = LayoutInflater.from(parent.context).inflate(R.layout.listview_subject,parent,false)
        return SubjectViewHolder(v,subjectcallback)
    }

    class SubjectViewHolder(val itView : View,val subjectcallback : SubjectCallback) : RecyclerView.ViewHolder(itView) {

        var viewsubjectlist = itView.findViewById(R.id.viewsubject) as TextView

        fun bindsubjectitem(subjectmodel : SubjectModel){

            viewsubjectlist?.text =  subjectmodel.subjectname

            itView?.setOnClickListener{
                subjectcallback.onclickSubject(subjectmodel)
            }
        }
    }

    interface SubjectCallback {

        fun onclickSubject(subjectmodel: SubjectModel)
    }
}