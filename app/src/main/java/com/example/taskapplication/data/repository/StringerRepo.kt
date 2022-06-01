package com.example.taskapplication.data.repository

import com.example.taskapplication.data.api.PostApiService
import com.example.taskapplication.data.model.StringerAdd
import com.example.taskapplication.data.model.StringerDelete
import com.example.taskapplication.data.model.StringerListItem
import com.example.taskapplication.data.model.StringerUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * Created by bhupendrapatel on 31/05/22.
 */
class StringerRepo @Inject constructor(private val postApiService: PostApiService) {

    //GetStringer
    fun stringer(auth: String, shopId: String): Flow<MutableList<StringerListItem>> = flow {
        val response = postApiService.getStringer(auth, shopId)
        emit(response)
    }.flowOn(Dispatchers.IO)

    //DeleteStringer
    fun deleteStringer(
        auth: String, shopId: String, stringerDelete: StringerDelete
    ): Flow<StringerDelete> = flow {
        val response = postApiService.deleteStringer(auth, shopId, stringerDelete)
        emit(response)
    }.flowOn(Dispatchers.IO)

    //AddStringer
    fun addStringer(
        auth: String, shopId: String, stringerAdd: StringerAdd
    ): Flow<StringerAdd> = flow {
        val response = postApiService.addStringer(auth, shopId, stringerAdd)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun upDateStringer(
        auth: String, shopId: String, stringerUpdate: StringerUpdate
    ): Flow<StringerUpdate> = flow {
        val response = postApiService.upDateStringer(auth, shopId, stringerUpdate)
        emit(response)
    }.flowOn(Dispatchers.IO)
}