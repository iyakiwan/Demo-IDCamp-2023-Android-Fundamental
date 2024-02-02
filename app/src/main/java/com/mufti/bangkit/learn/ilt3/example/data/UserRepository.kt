package com.mufti.bangkit.learn.ilt3.example.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mufti.bangkit.learn.ilt3.example.data.local.reference.SharedPreference
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.remote.mapper.UserMapper

class UserRepository private constructor(
    private val apiService: ApiService,
    private val preference: SharedPreference
) {

    fun getListUser(): LiveData<Result<List<User>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getListUsers("1")
            val dataResult = UserMapper.mapListUserResponseToListUser(response)

            emit(Result.Success(dataResult))
        } catch (e: Exception) {
            Log.d("UserRepository", "getListUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getLocalUser() = preference.getUser()

    fun setLocalUser(user: User) = preference.setUser(user)

    fun getIsLogin() = preference.getIsLogin()

    fun setIsLogin(login: Boolean) = preference.setIsLogin(login)

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            preference: SharedPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, preference)
            }.also { instance = it }
    }
}