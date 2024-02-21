package com.example.checklist.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.R
import com.example.checklist.data.Chore
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CheckListActivity : AppCompatActivity() {

    private val adapter: CheckListAdapter by lazy {
        CheckListAdapter(::onListChoreClicked)
    }

    private val viewModel : CheckListViewModel by lazy {
        CheckListViewModel.create(application)
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result : ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val choreAction = data?.getSerializableExtra(ITEM_ACTION_RESULT) as ChoreAction

            viewModel.execute(choreAction)
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
        val listObserver = Observer<List<Chore>> { listChores ->
            adapter.submitList(listChores)
        }
        viewModel.choreListLiveData.observe(this@CheckListActivity, listObserver)
    }

    private fun onListChoreClicked (chore: Chore) {
        openCheckListDetails(chore)
    }

    private fun openCheckListDetails (chore: Chore? = null) {
        val intent = ChoreDetailsActivity.start(this, chore)
        startForResult.launch(intent)
    }
}
