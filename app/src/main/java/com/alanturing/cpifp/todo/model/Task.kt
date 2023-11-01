package com.alanturing.cpifp.todo.model

data class Task(public val id:Int,
                public val title:String,
                public val description:String,
                public val isCompleted: Boolean)
