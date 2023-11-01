package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

interface TaskClickedListener {
    fun onTaskClicked(task: Task)
    fun onTaskLongClicked(task: Task)
}

class TasksAdapter(val tasks: List<Task>, private val taskClickedListener: TaskClickedListener) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bindTask(task)
        // Bind clickListener to holder
        holder.itemView.setOnClickListener { taskClickedListener.onTaskClicked(task) }
        holder.itemView.setOnLongClickListener { taskClickedListener.onTaskLongClicked(task); true }
    }
}