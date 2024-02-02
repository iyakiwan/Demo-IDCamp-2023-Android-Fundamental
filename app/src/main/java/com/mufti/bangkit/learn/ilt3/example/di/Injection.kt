package com.mufti.bangkit.learn.ilt3.example.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import com.mufti.bangkit.learn.ilt3.example.data.local.datastore.SettingPreferences
import com.mufti.bangkit.learn.ilt3.example.data.local.reference.SharedPreference
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiConfig

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val preference = SharedPreference.getInstance(context)
        val dataStore = SettingPreferences.getInstance(context.dataStore)
        return UserRepository.getInstance(
            apiService = apiService,
            preference = preference,
            dataStore = dataStore
        )
    }
}