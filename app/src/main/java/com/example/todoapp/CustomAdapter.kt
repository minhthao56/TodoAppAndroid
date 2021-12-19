package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val mList: List<ItemsViewModel>,
    private val cellClickListener: CellClickListener
):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.titleCard.text = itemsViewModel.title
        holder.descriptionCard.text = itemsViewModel.description
        holder.createCard.text = itemsViewModel.createAt

        if (itemsViewModel.isFinish){
            holder.containerCard.setBackgroundResource(R.drawable.style_card_todo_done)
            holder.clockCard.isVisible = false
        }

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(itemsViewModel)
        }

    }



    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleCard: TextView =itemView.findViewById(R.id.textTitleCard)
        val descriptionCard: TextView = itemView.findViewById(R.id.textDescription)
        val createCard: TextView = itemView.findViewById(R.id.textCreateAt)
        val  containerCard: LinearLayout= itemView.findViewById(R.id.containerCard)
        val  clockCard: ImageView= itemView.findViewById(R.id.clockCard)
    }
}