package com.rohitksingh.retrofit_coroutine.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rohitksingh.retrofit_coroutine.models.Todo
import com.rohitksingh.retrofit_coroutine.repositories.TodoRepository

class MainViewModel : ViewModel(){

    val repository :   TodoRepository = TodoRepository()

    fun getTodo(): LiveData<Todo>{
        return repository.getAllTodo(1)
    }

}