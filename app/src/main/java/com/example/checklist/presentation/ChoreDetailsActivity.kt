package com.example.checklist.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.checklist.R
import com.example.checklist.data.Chore
import com.google.android.material.snackbar.Snackbar

class ChoreDetailsActivity : AppCompatActivity () {
    private var chore : Chore? = null
    private lateinit var btnSave: Button

    companion object {
        private const val ITEM_DETAIL_EXTRA = "item.extra.detail"

        fun start(context: Context, chore: Chore?): Intent {
            val intent = Intent(context, ChoreDetailsActivity::class.java)
                .apply {
                    putExtra(ITEM_DETAIL_EXTRA, chore)
                }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chore_details)

        chore = intent.getSerializableExtra(ITEM_DETAIL_EXTRA) as Chore?

        val choreName = findViewById<EditText>(R.id.choreName)
        val choreDescription = findViewById<EditText>(R.id.choreDescription)
        btnSave = findViewById(R.id.btnSave)

        if (chore != null) {
            choreName.setText(chore!!.name)
            choreDescription.setText(chore!!.description)
        }

        btnSave.setOnClickListener {
            val name = choreName.text.toString()
            val description = choreDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                if (chore == null) {
                    addOrUpdateChore(0, name, description, ActionType.CREATE)
                } else {
                    addOrUpdateChore(chore!!.id, name, description, ActionType.UPDATE)
                }
            }else {
                showMessage(it, "Missing required fields")
            }
        }
    }

    private fun addOrUpdateChore(
        id: Int,
        name: String,
        description: String,
        actionType: ActionType
    ) {
        val chore = Chore(id, name, description)
        returnAction(chore, ActionType.CREATE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_chore_detail, menu)
        return true
    }



    private fun returnAction(chore: Chore, actionType: ActionType) {
        val intent = Intent()
            .apply {
                val choreAction = ChoreAction(chore, actionType.name)
                putExtra("ITEM_ACTION_RESULT", choreAction)
            }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_chore -> {

                if (chore != null) {
                    returnAction(chore!!, ActionType.DELETE)
                } else {
                    showMessage(btnSave, "Item not found")
                }
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}