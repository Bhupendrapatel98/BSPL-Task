package com.example.taskapplication.data.api

import com.example.taskapplication.data.model.StringerAdd
import com.example.taskapplication.data.model.StringerDelete
import com.example.taskapplication.data.model.StringerListItem
import com.example.taskapplication.data.model.StringerUpdate
import retrofit2.http.*

/**
 * Created by bhupendrapatel on 31/05/22.
 */

interface PostApiService {

    @GET("stringerlist")
    suspend fun getStringer(
        @Header("Authorization") Authorization: String,
        @Header("ShopID") ShopID: String
    ): MutableList<StringerListItem>

    @HTTP(method = "DELETE", path = "stringerDetail", hasBody = true)
    suspend fun deleteStringer(
        @Header("Authorization") Authorization: String,
        @Header("ShopID") ShopID: String,
        @Body stringerDelete: StringerDelete
    ): StringerDelete

    @POST("stringerDetail")
    suspend fun addStringer(
        @Header("Authorization") Authorization: String,
        @Header("ShopID") ShopID: String,
        @Body stringerAdd: StringerAdd
    ): StringerAdd

    @PUT("stringerDetail")
    suspend fun upDateStringer(
        @Header("Authorization") Authorization: String,
        @Header("ShopID") ShopID: String,
        @Body stringerUpdate: StringerUpdate
    ): StringerUpdate


}