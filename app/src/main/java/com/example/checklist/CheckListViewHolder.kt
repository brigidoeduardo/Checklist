package com.example.checklist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheckListViewHolder (
    private val view: View
) : RecyclerView.ViewHolder(view) {
    private val tvItemName = view.findViewById<TextView>(R.id.tvItemName)
    private val tvItemDesc = view.findViewById<TextView>(R.id.tvItemDesc)

    fun bind (
        item : Item,
        openItemDetailsActivity: (item:Item) -> Unit){
        tvItemName.text = item.name
        tvItemDesc.text = item.description

        view.setOnClickListener {
            openItemDetailsActivity.invoke(item)
        }
    }
}