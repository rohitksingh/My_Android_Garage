package com.rohitksingh.retrofit_coroutine.networks

import com.rohitksingh.retrofit_coroutine.models.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {

    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id")  todoId: Int): Call<Todo>

}