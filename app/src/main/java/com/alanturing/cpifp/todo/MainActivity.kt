package com.alanturing.cpifp.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tasks = TaskLocalRepository.getInstance().tasks
        val taskRecycler = binding.tasksRv
        taskRecycler.adapter = TasksAdapter(tasks)

        binding.createTodo.setOnClickListener {
            val intent = Intent(this, CreateToDoActivity::class.java)
            startActivity(intent)
        }
        //TODO("Crear vista de detalles")
    }

    override fun onResume() {
        super.onResume()
        val tasks = TaskLocalRepository.getInstance().tasks
        val taskRecycler = binding.tasksRv
        taskRecycler.adapter = TasksAdapter(tasks)
    }
}