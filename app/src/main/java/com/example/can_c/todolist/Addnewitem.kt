package com.example.can_c.todolist


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_addnewitem.*
import java.util.*


class Addnewitem : AppCompatActivity() {

    lateinit var db : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewitem)

        db = FirebaseDatabase.getInstance().reference
    }

    fun addTodoItem(view : View){
        val todoItem = ToDoItem.create()
        val dateString = dateText.getText().toString()

        if(dateString.equals("")){
            Toast.makeText(this,"Please enter date",Toast.LENGTH_SHORT).show()
        }
        else{
            val date = Date.parse(dateString)


            todoItem.itemText = todoText.text.toString()
            todoItem.startDate = dateString
            todoItem.startDateDouble = date
            todoItem.isDone = false


            val newItem = db.child(Constants.FIREBASE_ITEM).push()
            todoItem.objcetId = newItem.key

            newItem.setValue(todoItem)

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun onDatePress(view: View){
        val newFragment = DatepickerFragment()
        newFragment.show(fragmentManager,"Date Picker")
    }


}
