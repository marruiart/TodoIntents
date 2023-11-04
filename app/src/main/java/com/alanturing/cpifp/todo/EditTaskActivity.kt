package com.alanturing.cpifp.todo

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditTaskBinding
import com.alanturing.cpifp.todo.model.Task

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = TaskLocalRepository.getInstance()

        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("task", Task::class.java)
        } else {
            intent.extras!!.getParcelable("task")
        }
        binding.descriptionInput.setText(task!!.description)
        binding.nameInput.setText(task!!.title)
        binding.statusSwitch.isChecked = task!!.isCompleted
        binding.statusSwitch.text = if (task!!.isCompleted) "Completada" else "Pendiente"

        binding.submitBtn.setOnClickListener {
            val updatedTask = Task(task.id, binding.nameInput.text.toString(), binding.descriptionInput.text.toString(), binding.statusSwitch.isChecked)
            repository.update(updatedTask)
            setResult(Activity.RESULT_OK) // define the result for callback
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}