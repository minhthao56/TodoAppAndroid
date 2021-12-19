package com.example.todoapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.GridView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*
import java.util.Calendar
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), CellClickListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<Toolbar>(R.id.myToolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);

        val recyclerViewListTodo = findViewById<RecyclerView>(R.id.recyclerViewListTodo)
        val buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAdd)
        val buttonAddTime = findViewById<Button>(R.id.buttonAddTime)


        recyclerViewListTodo.layoutManager = LinearLayoutManager(this
        )

        val data = ArrayList<ItemsViewModel>()

        val listTodoJson= resources.openRawResource(R.raw.todos).bufferedReader().use { it.readText() }

        var gson = Gson()

        val typeToken = object : TypeToken<List<ItemsViewModel>>() {}.type
        var listTodo = gson.fromJson<List<ItemsViewModel>>(listTodoJson, typeToken)

        val size = listTodo.size

        for (i in 0 until size) {
            data.add(ItemsViewModel(listTodo[i].title, listTodo[i].description, listTodo[i].createAt, listTodo[i].isFinish))
        }

        val adapter = CustomAdapter(data, this)

        recyclerViewListTodo.adapter = adapter



        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_dialog)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, state: Int) {


                    when (state) {
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        }
                        BottomSheetBehavior.STATE_COLLAPSED->{
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        }
                    }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })


        buttonAdd.setOnClickListener {
            bottomSheetBehavior.state =  BottomSheetBehavior.STATE_EXPANDED
        }

        buttonAddTime.setOnClickListener {
            openDialogCalendar()
        }





    }

    override fun onCellClickListener(item: ItemsViewModel) {

        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)

    }

    private fun openDialogCalendar(){

        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.activity_calendar)
        dialog.show()

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT )


        val gridCalendar = dialog.findViewById<GridView>(R.id.gridCalendar)
        val dataDate =   ArrayList<ItemsGridCalendar>()

        val currentCal = Calendar.getInstance()


        val maxDateOfCurrentMonth =currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);

        currentCal.set(Calendar.DAY_OF_MONTH, currentCal.getActualMaximum(Calendar.DAY_OF_MONTH));

        val lastDayOfCurrentMonth = currentCal.time.day

        currentCal.add(Calendar.MONTH, -1);
        val maxDateOfPrevMonth =currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);

        currentCal.add(Calendar.MONTH, 1);
        val maxDateOfNextMonth =currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);


        Log.i("maxDateOfMonth", maxDateOfCurrentMonth.toString())
        Log.i("maxPrevDateOfMonth", maxDateOfPrevMonth.toString())
        Log.i("maxNextDateOfMonth", maxDateOfNextMonth.toString())
        Log.i("lastDayOfCurrentMonth", lastDayOfCurrentMonth.toString())

        currentCal.set(Calendar.DAY_OF_MONTH, 1);
        val startDayOfMonth = currentCal.time.day

        Log.i("startDayOfMonth", startDayOfMonth.toString())

        val stringDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri","Sat")

        stringDays.forEach {
            dataDate.add(ItemsGridCalendar(it))
        }


        for (i in maxDateOfPrevMonth - startDayOfMonth + 1 until  maxDateOfPrevMonth + 1){
            dataDate.add(ItemsGridCalendar((i.toString())))
        }

        for (i in 1..maxDateOfCurrentMonth){
            dataDate.add(ItemsGridCalendar((i.toString())))
        }

        for (i in 1..6 - lastDayOfCurrentMonth ){
            dataDate.add(ItemsGridCalendar((i.toString())))
        }

        val adapterCalendar = GirdCalendarAdapter(this, dataDate)
        gridCalendar.adapter = adapterCalendar


    }



}