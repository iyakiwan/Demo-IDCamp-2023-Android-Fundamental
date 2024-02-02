package com.mufti.bangkit.learn.ilt3.example.di

import android.content.Context
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import com.mufti.bangkit.learn.ilt3.example.data.local.reference.SharedPreference
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val preference = SharedPreference.getInstance(context)
        return UserRepository.getInstance(
            apiService = apiService,
            preference = preference)
    }
}