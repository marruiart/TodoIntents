package com.alanturing.cpifp.todo.model

data class Task(private val id:Int,
                private val title:String,
                private val description:String,
                private val isCompleted: Boolean)
