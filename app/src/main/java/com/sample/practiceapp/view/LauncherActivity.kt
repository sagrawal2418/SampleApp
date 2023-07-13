package com.sample.practiceapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sample.practiceapp.R
import com.sample.practiceapp.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btntoDos.setOnClickListener {

            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.container, ToDoFragment(), "TODO_FRAGMENT")
            transaction.addToBackStack(null)
            transaction.commit()

        }
        binding.btnUsers.setOnClickListener {

            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.container, UserFragment(), "USER_FRAGMENT")
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}