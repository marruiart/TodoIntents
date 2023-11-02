package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.alanturing.cpifp.todo.adapter.TaskClickedListener
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    updateRecyclerView()
                }

                Activity.RESULT_CANCELED -> {
                    Snackbar.make(this, binding.root, "Se ha cancelado", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateRecyclerView()
        setOnClickCreateTask()
        //TODO("Crear vista de detalles")
    }

    private fun setOnClickCreateTask() {
        binding.createTodo.setOnClickListener {
            val intent = Intent(this, CreateToDoActivity::class.java)
            getResult.launch(intent)
        }
    }

    private fun updateRecyclerView() {
        val tasks = TaskLocalRepository.getInstance().tasks
        val taskRecycler = binding.tasksRv
        taskRecycler.adapter = TasksAdapter(
            tasks,
            object : TaskClickedListener {
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
            },
            ::onShareItem, // pass a function through parameters -> member operator
            ::onEditItem
        )
    }

    fun onEditItem(task: Task, view: View) {

    }

    fun onShareItem(task: Task, view: View) {
        val statusText = if (task.isCompleted) "Completada" else "Pendiente"
        val shareText = getString(R.string.share_text,task.title, task.description, statusText)

        val intent = Intent().apply {
            action = Intent.ACTION_SEND // this intent is going to send sth
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, null) // shows all apps available to share
        startActivity(shareIntent)
    }
}