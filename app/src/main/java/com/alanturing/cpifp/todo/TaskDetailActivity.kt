package com.alanturing.cpifp.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.databinding.ActivityTaskDetailBinding

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskTitle: String = intent.getStringExtra("task").toString()
        binding.textView.text = taskTitle
    }
}