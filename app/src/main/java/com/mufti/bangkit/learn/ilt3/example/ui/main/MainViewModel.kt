package com.mufti.bangkit.learn.ilt3.example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.Result
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    val listUser: LiveData<Result<List<User>>> = repository.getListUser()

    fun setLocalUser(user: User) = repository.setLocalUser(user)

    fun getLocalUser() = repository.getLocalUser()

    fun getIsLogin() = repository.getIsLogin()

    fun setIsLogin(login: Boolean) = repository.setIsLogin(login)
}