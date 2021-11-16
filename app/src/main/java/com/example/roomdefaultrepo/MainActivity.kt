package com.example.roomdefaultrepo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.roomdefaultrepo.database.AppDataBase
import com.example.roomdefaultrepo.database.Memo
import com.example.roomdefaultrepo.databinding.ActivityMainBinding
import com.example.roomdefaultrepo.vm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var db : AppDataBase
    private lateinit var memoList : LiveData<List<Memo>>
    private val  viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.saveDataButton.setOnClickListener {
            val content : String = findViewById<EditText>(R.id.saveDateEditText).text.toString()
            viewModel.insertData(Memo(content))
        }
        //데이터를 삭제하였을 때
        binding.deleteDataButton.setOnClickListener {
            //memoList = viewModel.getAllData()
            val index : Int = binding.deleteDataEditText.text.toString().toInt()
            val deleteData : Memo = viewModel.getAllData().value!![index]

            viewModel.deleteData(deleteData)
            Log.d(TAG, "onCreate: ${viewModel.getAllData().value}")
        }
    }

//    private fun loadData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            //비동기 코드 뒤에서 돌아가는 코드 레트로핏 서버 연동, 테이블 관련 작업(db)
//            memoList = db.memoDao().getAll()
//            contentList = db.memoDao().getAllContent()
//            runOnUiThread {
//                findViewById<TextView>(R.id.resultDataText).text = contentList.toString()
//            }
//        }
//    }
}