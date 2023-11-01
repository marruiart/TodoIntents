package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateToDoBinding
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

class TasksAdapter(val datos: List<Task>) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t: Task) {
            binding.taskName.text = t.title
            binding.taskDescription.text = t.description
            binding.taskState.isChecked = t.isCompleted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = datos.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = datos[position]
        holder.bindTask(task)
    }
}