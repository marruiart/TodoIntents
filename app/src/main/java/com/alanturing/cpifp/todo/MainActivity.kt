package com.alanturing.cpifp.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alanturing.cpifp.todo.adapter.TaskClickedListener
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateRecyclerView()
        setOnClickCreateTask()
        //TODO("Crear vista de detalles")
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }

    private fun setOnClickCreateTask() {
        binding.createTodo.setOnClickListener {
            val intent = Intent(this, CreateToDoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateRecyclerView() {
        val tasks = TaskLocalRepository.getInstance().tasks
        val taskRecycler = binding.tasksRv
        taskRecycler.adapter = TasksAdapter(tasks, object : TaskClickedListener {
            // Implement listener interface
            override fun onTaskClicked(task: Task) {
                val intent = Intent(this@MainActivity, TaskDetailActivity::class.java)
                intent.putExtra("task", task.title)
                startActivity(intent)
            }

            override fun onTaskLongClicked(task: Task) {
                TaskLocalRepository.getInstance().delete(task.id)
                updateRecyclerView()
            }
        })
    }
}