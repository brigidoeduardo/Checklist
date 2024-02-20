package com.example.checklist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ItemDetailsActivity : AppCompatActivity (){
    private var item : Item = null
    private lateinit var btnSave : Button

    companion object {
        private const val ITEM_DETAIL_EXTRA = "item.extra.detail"

        fun start (context : Context, item: Item): Intent {
            val intent = Intent (context, ItemDetailsActivity::class.java)
                .apply {
                    putExtra(ITEM_DETAIL_EXTRA, item)
                }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}