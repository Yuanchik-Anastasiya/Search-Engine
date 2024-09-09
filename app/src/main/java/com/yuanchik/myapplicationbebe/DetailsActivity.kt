package com.yuanchik.myapplicationbebe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class DetailsActivity : AppCompatActivity() {
    private val details_toolbar = findViewById<Toolbar>(R.id.details_toolbar)
    private val details_poster = findViewById<ImageView>(R.id.details_poster)
    private val details_description = findViewById<TextView>(R.id.details_description)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setFilmsDetails()
    }
    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film

        details_toolbar.title = film.title
        details_poster.setImageResource(film.poster)
        details_description.text = film.description
    }
}