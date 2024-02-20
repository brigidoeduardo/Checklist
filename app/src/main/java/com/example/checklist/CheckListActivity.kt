package com.example.checklist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CheckListActivity : AppCompatActivity() {

    private val adapter: CheckListAdapter by lazy {
        CheckListAdapter(::onListItemClicked)
    }

    private val viewModel : CheckListViewModel by lazy {
        CheckListViewModel.create(application)
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result : ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val itemAction = data?.getSerializableExtra(ITEM_ACTION_RESULT) as ItemAction

            viewModel.execute(itemAction)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val rvChecklist : RecyclerView = findViewById(R.id.rvChecklist)
        rvChecklist.adapter = adapter

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fabAdd)

        floatingActionButton.setOnClickListener {
            openCheckListDetails()
        }
    }

    override fun onStart() {
        super.onStart()
        listFromDatabase()
    }

    private fun listFromDatabase () {
        val listObserver = Observer<List<Item>> { listItems ->
            adapter.submitList(listItems)
        }
        viewModel.checkListLiveData.observe(this@MainActivity, listObserver)
    }

    private fun onListItemClicked (item:Item) {
        openCheckListDetails(item)
    }

    private fun openCheckListDetails (item: Item? = null) {
        val intent = ItemDetailsActivity.start (this, item)
        startForResult.launch(intent)
    }
}
