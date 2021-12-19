package com.example.todoapp


import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class GirdCalendarAdapter(
    var context: Context,
    private val mList: ArrayList<ItemsGridCalendar>,
):BaseAdapter() {
    override fun getCount(): Int {

        return  mList.size
    }

    override fun getItem(position: Int): Any {

        return mList[position]
    }

    override fun getItemId(position: Int): Long {

        return  position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View = View.inflate(context, R.layout.cell_calendar, null)

        var textCellDate = view.findViewById<TextView>(R.id.textCellDate)

        var itemCellDate = mList[position]

        val currentTime = Calendar.getInstance().time

        textCellDate.text = itemCellDate.date

        if (itemCellDate.date == currentTime.date.toString()){
            textCellDate.setBackgroundResource(R.drawable.style_current_day)
            textCellDate.setTextColor(Color.WHITE)
        }

        return  view
    }

}