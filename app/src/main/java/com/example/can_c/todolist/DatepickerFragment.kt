package com.example.can_c.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.text.DateFormat
import java.util.*

class DatepickerFragment:DialogFragment(),DatePickerDialog.OnDateSetListener {
    private lateinit var calendar: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                this,year,month,day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val startDate = formatDate(year,month,day)
        activity.findViewById<EditText>(R.id.dateText).setText(startDate)
    }

    private fun formatDate(year: Int,month: Int,day: Int):String{
        calendar.set(year,month,day,0,0,0)
        val chosenDate = calendar.time

        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }

}