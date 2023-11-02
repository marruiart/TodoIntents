package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

interface TaskClickedListener {
    fun onTaskClicked(task: Task)
    fun onTaskLongClicked(task: Task)
}

class TasksAdapter(
    private val tasks: List<Task>,
    private val taskClickedListener: TaskClickedListener,
    private val onShare: ((t: Task, v: View) -> Unit), // onShare function
    private val onEdit: ((t: Task, v: View) -> Unit)
) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t: Task) {
            binding.taskName.text = t.title
            binding.taskDescription.text = t.description
            binding.taskState.isChecked = t.isCompleted
            // Bind share button with onShare function
            binding.shareBtn.setOnClickListener {
                onShare(t, it)
            }
            binding.editBtn.setOnClickListener {
                onEdit(t, it)
            }
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