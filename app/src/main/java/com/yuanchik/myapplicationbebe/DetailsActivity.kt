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
import androidx.core.view.ViewCompat.*
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.yuanchik.myapplicationbebe.databinding.ActivityDetailsBinding
import com.yuanchik.myapplicationbebe.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {
    private var binding: ActivityDetailsBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        setFilmsDetails()
    }
    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film

        binding?.detailsToolbar?.title = film.title
        binding?.detailsPoster?.setImageResource(film.poster)
        binding?.detailsDescription?.text = film.description
    }
}