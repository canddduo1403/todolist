package com.example.can_c.todolist

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_todoitem.view.*


class FBTodoAdapter(options: FirebaseRecyclerOptions<ToDoItem>)
    : FirebaseRecyclerAdapter<ToDoItem, FBTodoAdapter.ViewHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_todoitem,parent,false)

        return FBTodoAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int, model: ToDoItem) {
        holder.bind(model)
    }


    class ViewHolder(v: View):RecyclerView.ViewHolder(v){

        companion object {
            var db = FirebaseDatabase.getInstance().reference
        }

        private var view : View = v

        fun bind(todoitem : ToDoItem){

            view.todoItemText.text = todoitem.itemText
            view.dateText.text = todoitem.startDate

            //view.edit_btn.visibility = View.INVISIBLE
            view.finished_btn.visibility = View.INVISIBLE
            view.delete_btn.visibility = View.INVISIBLE

            view.checkBox.setOnClickListener{
                if(view.checkBox.isChecked){
                    //view.edit_btn.visibility = View.VISIBLE
                    view.finished_btn.visibility = View.VISIBLE
                    view.delete_btn.visibility = View.VISIBLE
                }
                else{
                    //view.edit_btn.visibility = View.INVISIBLE
                    view.finished_btn.visibility = View.INVISIBLE
                    view.delete_btn.visibility = View.INVISIBLE
                }

                view.delete_btn.setOnClickListener{
                   deleteItem(todoitem)
                }

                view.finished_btn.setOnClickListener{
                    changeItemStatus(todoitem)
                }


            }

            if(todoitem.isDone == true){
                view.checkBox.isEnabled = false
                view.checkBox.setButtonDrawable(R.mipmap.done)
                view.delete_btn.visibility = View.VISIBLE
                view.delete_btn.setOnClickListener{
                    deleteItem(todoitem)
                }
            }
        }

        fun deleteItem(todoitem : ToDoItem){
            db.child(Constants.FIREBASE_ITEM).child(todoitem.objcetId).removeValue()
        }

        fun changeItemStatus(todoitem: ToDoItem){
            db.child(Constants.FIREBASE_ITEM).child(todoitem.objcetId).child("done").setValue(true)
        }

    }

}