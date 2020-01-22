package com.qi.student.ClassDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Build
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import com.qi.student.R


class StudentClass : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        lateinit var insertClass : InsertClassData
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classname)

        var recycleView : RecyclerView ?= null

        var addClassButton : Button?= null

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        //recycleView = findViewById(R.id.recycleviewer)

        /* set Layout*/
        //recycleView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        /* create a arraylist to save the data */
        //val className = ArrayList<ClassModel>()

        /* add the classname here*/
//        className.add(ClassModel("class 1"))
//        className.add(ClassModel("class 2"))
//        className.add(ClassModel("class 3"))
//        className.add(ClassModel("class 4"))
//        className.add(ClassModel("class 5"))
//        className.add(ClassModel("class 6"))
//        className.add(ClassModel("class 7"))
//        className.add(ClassModel("class 8"))
//        className.add(ClassModel("class 9"))
//        className.add(ClassModel("class 10"))
//        className.add(ClassModel("class 11"))
//        className.add(ClassModel("class 12"))



        /* create a adapter*/
//        val classAdapter = ClassNameAdapter(className)
//
//        /* now adding adapter*/
//
//        recycleView.adapter = classAdapter
//
        addClassButton = findViewById(R.id.addbutton)

        insertClass = InsertClassData(this)

        addClassButton?.setOnClickListener{
            //startActivity(Intent(this,AddClassActivity :: class.java))
            startActivityForResult(Intent(this, AddClassActivity:: class.java),1)
        }
        view()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1) {
            view()
        }
    }

    private fun view(){

        var classList = insertClass.fetchData(this)
        Log.v("check","index check list == $classList")
        var adapter = ClassNameAdapter(classList)
        var rv : RecyclerView = findViewById(R.id.recycleviewer)
        rv.adapter = adapter
    }

}