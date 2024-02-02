package com.mufti.bangkit.learn.ilt3.example.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mufti.bangkit.learn.ilt3.example.data.Result
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.ilt3.example.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]

        setupRecyclerView()

        observerListUser()

        setupView()
    }

    private fun setupView() {
        binding.floatingActionButton.setOnClickListener{
            val dataUser = viewModel.getLocalUser()
            val formatToast = if (dataUser.firstName.isNotEmpty() && dataUser.lastName.isNotEmpty())
                "${dataUser.firstName} ${dataUser.lastName} is selected!"
            else "No user selected!"
            Toast.makeText(this@MainActivity, formatToast, Toast.LENGTH_SHORT).show()
        }

        binding.fabClean.setOnClickListener{
            viewModel.setLocalUser(User(
                id = 0,
                email = "",
                firstName = "",
                lastName = "",
                avatar = ""
            ))
            Toast.makeText(this@MainActivity, "User cleaned!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter()

        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        adapter.setOnUserSelected {
            viewModel.setLocalUser(it)
        }
    }

    private fun observerListUser() {
        viewModel.listUser.observe(this){
            when(it){
                is Result.Loading -> {
                    binding.pvUsers.isVisible = true
                }
                is Result.Success -> {
                    binding.pvUsers.isVisible = false
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    binding.pvUsers.isVisible = false
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}