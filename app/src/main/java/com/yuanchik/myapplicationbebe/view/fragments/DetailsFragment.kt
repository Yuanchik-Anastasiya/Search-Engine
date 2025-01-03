package com.yuanchik.myapplicationbebe.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yuanchik.myapplicationbebe.data.ApiConstants
import com.yuanchik.myapplicationbebe.R
import com.yuanchik.myapplicationbebe.databinding.FragmentDetailsBinding
import com.yuanchik.myapplicationbebe.domain.Film

class DetailsFragment : Fragment() {
    private var film: Film? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        binding.detailsFabFavorites.setOnClickListener{
            if (!film?.isInFavorites!!){
                binding.detailsFabFavorites.setImageResource(R.drawable.round_favorite)
                film?.isInFavorites = true
            }else{
                binding.detailsFabFavorites.setImageResource(R.drawable.border_favorite)
                film?.isInFavorites = false
            }
        }

        binding.detailsFab.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Посмотри этот фильм: ${film?.title} \n\n ${film?.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Поделиться с:"))
        }
    }

    private fun setFilmsDetails() {
        film = arguments?.get("film") as Film

        binding.detailsToolbar.title = film!!.title
        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + film!!.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        binding.detailsDescription.text = film!!.description

        binding.detailsFabFavorites.setImageResource(
            if (film!!.isInFavorites) R.drawable.round_favorite
            else R.drawable.border_favorite
        )
    }
}