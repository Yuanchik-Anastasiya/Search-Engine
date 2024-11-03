package com.yuanchik.myapplicationbebe.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplicationbebe.view.rv_adapters.FilmListRecyclerAdapter
import com.yuanchik.myapplicationbebe.view.rv_adapters.TopSpacingItemDecoration
import com.yuanchik.myapplicationbebe.databinding.FragmentFavoritesBinding
import com.yuanchik.myapplicationbebe.domain.Film
import com.yuanchik.myapplicationbebe.utils.AnimationHelper
import com.yuanchik.myapplicationbebe.view.MainActivity

class FavoritesFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private var binding1: FragmentFavoritesBinding? = null

    private val binding get() = binding1!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding1 = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding1 = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList: List<Film> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(binding.favoritesFragmentRoot, requireActivity(),2)


        binding.favoritesRecycler
            .apply {
                filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(requireContext())

                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        filmsAdapter.addItems(favoritesList)
    }
}