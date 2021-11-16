package com.example.roomdefaultrepo.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.roomdefaultrepo.database.AppDataBase
import com.example.roomdefaultrepo.database.Memo
import com.example.roomdefaultrepo.repo.DbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val dbRepository : DbRepository = DbRepository(application)

    fun insertData(memo : Memo) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.insert(memo)
    }

    fun deleteData(memo: Memo) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.delete(memo)
    }

     fun getAllData() : LiveData<List<Memo>>{
        return dbRepository.memos
    }

    fun getAllContent() : LiveData<List<String>>{
        return dbRepository.memoList
    }
}