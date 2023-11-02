package com.alanturing.cpifp.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateToDoBinding
import com.alanturing.cpifp.todo.model.Task

class CreateToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = TaskLocalRepository.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityCreateToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val task = Task(
                repository.getNextTaskId(),
                binding.nameInput.text.toString(),
                binding.descriptionInput.text.toString(),
                false
            )
            repository.add(task)
            setResult(Activity.RESULT_OK) // define the result for callback
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}