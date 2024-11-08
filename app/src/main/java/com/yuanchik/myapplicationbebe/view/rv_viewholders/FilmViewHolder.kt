package com.yuanchik.myapplicationbebe.view.rv_viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yuanchik.myapplicationbebe.R
import com.yuanchik.myapplicationbebe.domain.Film
import com.yuanchik.myapplicationbebe.RatingDonutView


class FilmViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val ratingDonut = itemView.findViewById<RatingDonutView>(R.id.rating_donut)

    fun bind(film: Film) {
        title.text = film.title
        Glide.with(itemView)
            .load(film.poster)
            .centerCrop()
            .into(poster)
        description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}