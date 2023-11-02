package com.alanturing.cpifp.todo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) : Parcelable
