package com.mufti.bangkit.learn.ilt3.example.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: UserRepository) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> = pref.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkModeActive: Boolean) = viewModelScope.launch {
        pref.saveThemeSetting(isDarkModeActive)
    }
}