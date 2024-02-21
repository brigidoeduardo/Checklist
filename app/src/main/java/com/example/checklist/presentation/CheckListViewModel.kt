package com.example.checklist.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checklist.CheckListApplication
import com.example.checklist.data.Chore
import com.example.checklist.data.ChoreDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckListViewModel (
    private val choreDao: ChoreDao
) : ViewModel (){

    val choreListLiveData : LiveData <List<Chore>> = choreDao.getAll()

    fun execute (choreAction: ChoreAction) {
        when (choreAction.actionType) {
            ActionType.DELETE.name -> deleteById(choreAction.chore!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(choreAction.chore!!)
            ActionType.UPDATE.name -> updateIntoDataBase(choreAction.chore!!)
        }
    }
    private fun deleteById (id:Int){
        viewModelScope.launch (Dispatchers.IO) {
            choreDao.deleteById(id)
        }
    }

    private fun insertIntoDataBase(chore: Chore) {
        viewModelScope.launch(Dispatchers.IO) {
            choreDao.insert(chore)
        }
    }

    private fun updateIntoDataBase(chore: Chore) {
        viewModelScope.launch(Dispatchers.IO) {
            choreDao.update(chore)
        }
    }

    companion object {
        fun create(application: Application): CheckListViewModel {
            val dataBaseInstance = (application as CheckListApplication).getAppDataBase()
            val dao = dataBaseInstance.choreDao()
            return CheckListViewModel(dao)
        }
    }
}