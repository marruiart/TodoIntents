package com.alanturing.cpifp.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TODO("Recuperar el RecyclerView y asignar el adaptador")
        setContentView(R.layout.activity_main)
        TODO("Crear vista de detalles")
        TODO("Crear vista de formulario de creacón")
        TODO("Crear manejador de evento para navegar al formulario de crear")
    }
}