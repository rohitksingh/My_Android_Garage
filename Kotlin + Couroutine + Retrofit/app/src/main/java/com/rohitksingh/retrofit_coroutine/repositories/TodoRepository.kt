package com.rohitksingh.retrofit_coroutine.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohitksingh.retrofit_coroutine.models.Todo
import com.rohitksingh.retrofit_coroutine.networks.RetrofitClient
import com.rohitksingh.retrofit_coroutine.networks.Webservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository {

    val todoService : Webservice = RetrofitClient().webservice
    var todoLiveData : MutableLiveData<Todo> = MutableLiveData()


    fun getAllTodo(id: Int): LiveData<Todo>{

        val getRequestTodo : Call<Todo>  = todoService.getTodo(1)

        getRequestTodo.enqueue(object: Callback<Todo>{
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    todoLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return todoLiveData;

    }

}