package com.example.can_c.todolist

import java.sql.Time
import java.time.LocalDate
import java.util.*

//Hold data for todo list Item
class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }

    var objcetId: String? = null
    var itemText: String? = null
    var startDate: String? = null
    var startDateDouble: Long? = null
    var isDone: Boolean? = null
}