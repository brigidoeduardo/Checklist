package com.example.checklist

import java.io.Serializable

    data class ItemAction (
        val item : Item?,
        val actionType: String
) : Serializable

    enum class ActionType {
        DELETE,
        UPDATE,
        CREATE
}

const val ITEM_ACTION_RESULT = "ITEM_ACTION_RESULT"
