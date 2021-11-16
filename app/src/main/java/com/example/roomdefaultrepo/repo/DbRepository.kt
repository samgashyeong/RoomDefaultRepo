package com.example.roomdefaultrepo.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdefaultrepo.database.AppDataBase
import com.example.roomdefaultrepo.database.Memo
import com.example.roomdefaultrepo.database.MemoDao

class DbRepository(application: Application){

    private val db: AppDataBase = AppDataBase.getInstance(application)!!
    private val dao: MemoDao = db.memoDao()
    val memos: LiveData<List<Memo>> = dao.getAll()
    val memoList : LiveData<List<String>> = dao.getAllContent()


     fun insert(memo : Memo){
        dao.insertData(memo)
    }

    fun delete(memo : Memo){
        dao.deleteData(memo)
    }


}