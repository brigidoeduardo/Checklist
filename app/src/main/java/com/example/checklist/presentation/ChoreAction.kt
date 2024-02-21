package com.example.checklist.presentation

import com.example.checklist.data.Chore
import java.io.Serializable

    data class ChoreAction (
        val chore : Chore?,
        val actionType: String
) : Serializable

    enum class ActionType {
        DELETE,
        UPDATE,
        CREATE
}

const val ITEM_ACTION_RESULT = "ITEM_ACTION_RESULT"
