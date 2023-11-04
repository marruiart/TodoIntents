package com.alanturing.cpifp.todo.data

import android.util.Log
import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE: TaskLocalRepository? = null
        fun getInstance(): TaskLocalRepository {
            if (_INSTANCE == null) {
                _INSTANCE = TaskLocalRepository()
            }
            return _INSTANCE!!
        }
    }

    private var countId: Int = 2
    private val _tasks = mutableListOf<Task>()

    init {

        //TODO("Borrar cuando el repositorio este listo")
        _tasks.add(Task(1, "Comprar leche", "Leche desnatada", false))
        _tasks.add(Task(2, "Hacer práctica Android", "Completar todos los TODOS", false))

    }


    val tasks: List<Task>
        get() = _tasks

    fun add(task: Task) {
        _tasks.add(task)
    }

    fun delete(id: Int) {
        var task: Task? = null;
        _tasks.forEach { t ->
            if (t.id == id) {
                task = t
            }
        }
        if (task != null) {
            _tasks.remove(task)
        }
    }

    fun update(updatedTask: Task) {
        val index = _tasks.indexOfFirst { it.id == updatedTask.id }
        _tasks[index] = updatedTask
        Log.d("tasks", _tasks.toString())
    }

    fun getNextTaskId() = ++countId
}