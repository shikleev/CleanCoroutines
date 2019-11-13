package project.views.cleancoroutines.data.network

import project.views.cleancoroutines.data.ResponseWrapper
import project.views.cleancoroutines.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("5dcc12d554000064009c20fc")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ResponseWrapper<User>


    @GET("5dcc147154000059009c2104")
    suspend fun getUsersError(
        @Query("page") page: Int
    ): ResponseWrapper<User>



}