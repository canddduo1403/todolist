package com.example.can_c.todolist

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val db = FirebaseDatabase.getInstance()

    val adapter : FBTodoAdapter by lazy {
        val query = db.getReference("todo_item").orderByChild("startDateDouble")
        val options = FirebaseRecyclerOptions.Builder<ToDoItem>()
                .setQuery(query,ToDoItem::class.java)
                .setLifecycleOwner(this)
                .build()

        FBTodoAdapter(options)
    }

    private lateinit var calendar : Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val currentDate = changeDateFormat(currentYear,month,day)

        dateView.text = currentDate
    }

    override fun onStart() {
        super.onStart()

        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()

        adapter.startListening()
    }

    fun changePage(view: View){
        val intent = Intent(this,Addnewitem::class.java)
        startActivity(intent)
    }

    private fun changeDateFormat(year:Int,month:Int,day:Int):String{

        calendar.set(year,month,day,0,0,0)
        val currentDate = calendar.time

        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return dateFormat.format(currentDate)
    }

}
