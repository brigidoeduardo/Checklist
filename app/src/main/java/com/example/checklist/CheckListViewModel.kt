package com.example.checklist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckListViewModel (
    private val itemDao: ItemDao
) : ViewModel (){

    val itemListLiveData : LiveData <List<Item>> = itemDao.getAll()

    fun execute (itemAction: ItemAction) {
        when (itemAction.actionType) {
            ActionType.DELETE.name -> deleteById(itemAction.item!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(itemAction.item!!)
            ActionType.UPDATE.name -> updateIntoDataBase(itemAction.item!!)
        }
    }
    private fun deleteById (id:Int){
        viewModelScope.launch (Dispatchers.IO) {
            itemDao.deleteById(id)
        }
    }

    private fun insertIntoDataBase(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insert(item)
        }
    }

    private fun updateIntoDataBase(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.update(item)
        }
    }

    companion object {
        fun create(application: Application): CheckListViewModel {
            val dataBaseInstance = (application as CheckListApplication).getAppDataBase()
            val dao = dataBaseInstance.itemDao()
            return CheckListViewModel(dao)
        }
    }
}