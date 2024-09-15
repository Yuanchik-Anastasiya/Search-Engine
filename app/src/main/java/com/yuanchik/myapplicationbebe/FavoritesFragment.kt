package com.yuanchik.myapplicationbebe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplicationbebe.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private var fragmentFavoritesBinding: FragmentFavoritesBinding? = null
    private val binding get() = fragmentFavoritesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentFavoritesBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList: List<Film> = emptyList()

        binding.favoritesRecycler
            .apply {
                filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
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