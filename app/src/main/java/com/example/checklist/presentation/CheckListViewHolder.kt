package com.example.checklist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.R
import com.example.checklist.data.Chore


class CheckListViewHolder (
    private val view: View
) : RecyclerView.ViewHolder(view) {
    private val tvChoreName = view.findViewById<TextView>(R.id.tvChoreName)
    private val tvChoreDesc = view.findViewById<TextView>(R.id.tvChoreDesc)

    fun bind (
        chore: Chore,
        openItemDetailsActivity: (chore: Chore) -> Unit){
        tvChoreName.text = chore.name
        tvChoreDesc.text = chore.description

        view.setOnClickListener {
            openItemDetailsActivity.invoke(chore)
        }
    }
}