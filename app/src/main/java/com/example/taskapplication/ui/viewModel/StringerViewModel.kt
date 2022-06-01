package com.example.taskapplication.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapplication.data.model.StringerAdd
import com.example.taskapplication.data.model.StringerDelete
import com.example.taskapplication.data.model.StringerListItem
import com.example.taskapplication.data.model.StringerUpdate
import com.example.taskapplication.data.repository.StringerRepo
import com.example.taskapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by bhupendrapatel on 31/05/22.
 */

@HiltViewModel
class StringerViewModel @Inject constructor(private val stringerRepo: StringerRepo) : ViewModel() {

    private val _deleteLiveData = MutableLiveData<Resource<StringerDelete>>()
    val deleteLiveData: LiveData<Resource<StringerDelete>>
        get() = _deleteLiveData

    private val _postLiveData = MutableLiveData<Resource<MutableList<StringerListItem>>>()
    val postLiveData: LiveData<Resource<MutableList<StringerListItem>>>
        get() = _postLiveData

    private val _addLiveData = MutableLiveData<Resource<StringerAdd>>()
    val addLiveData: LiveData<Resource<StringerAdd>>
        get() = _addLiveData

    var address = MutableLiveData<String>()
    var age = MutableLiveData<String>()
    var closeTime = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var phoneNumber = MutableLiveData<String>()
    var startTime = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var stringerID = MutableLiveData<String>()


    fun upDateData(){
        val stringerUpdate = StringerUpdate(address.value.toString(),
            Integer.parseInt(age.value.toString()),closeTime.value.toString(),name.value.toString(),
            password.value.toString(),
            phoneNumber.value.toString(),Integer.parseInt(stringerID.value.toString()),startTime.value.toString(),67)

        viewModelScope.launch {
           // _addLiveData.value = Resource.loading()
            stringerRepo.upDateStringer("tpbf49bdlr9202103tsptatps", "1",stringerUpdate)
                .catch { e ->
                    Log.d("fgfgh", "upDateData: "+e.message.toString())
                   // _addLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    Log.d("kfuhdiufd", "sucess: ")
                   // _addLiveData.value = Resource.success(data)
                }
        }
    }

    fun addData(){
        val stringerAdd = StringerAdd(address.value.toString(),
            Integer.parseInt(age.value.toString()),closeTime.value.toString(),67,name.value.toString(),
        phoneNumber.value.toString(),startTime.value.toString(),1,
        password.value.toString(),55)

        viewModelScope.launch {
            _addLiveData.value = Resource.loading()
            stringerRepo.addStringer("tpbf49bdlr9202103tsptatps", "1",stringerAdd)
                .catch { e ->
                    _addLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    _addLiveData.value = Resource.success(data)
                }
        }
    }

    fun getData() {
        viewModelScope.launch {
            _postLiveData.value = Resource.loading()

            stringerRepo.stringer("tpbf49bdlr9202103tsptatps", "1")
                .catch { e ->
                    _postLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    _postLiveData.value = Resource.success(data)
                }
        }
    }

    fun deleteStringer(deleteId: Int) {
        val stringerDelete = StringerDelete(deleteId)

        viewModelScope.launch {
            _deleteLiveData.value = Resource.loading()

            stringerRepo.deleteStringer("tpbf49bdlr9202103tsptatps", "1", stringerDelete)
                .catch { e ->
                    _deleteLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    _deleteLiveData.value = Resource.success(data)
                }
        }
    }

}