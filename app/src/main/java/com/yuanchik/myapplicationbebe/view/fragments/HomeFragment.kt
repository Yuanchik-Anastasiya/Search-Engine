package com.yuanchik.myapplicationbebe.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplicationbebe.view.rv_adapters.FilmListRecyclerAdapter
import com.yuanchik.myapplicationbebe.R
import com.yuanchik.myapplicationbebe.view.rv_adapters.TopSpacingItemDecoration
import com.yuanchik.myapplicationbebe.databinding.FragmentHomeBinding
import com.yuanchik.myapplicationbebe.domain.Film
import com.yuanchik.myapplicationbebe.utils.AnimationHelper
import com.yuanchik.myapplicationbebe.view.MainActivity
import com.yuanchik.myapplicationbebe.viewmodel.HomeFragmentViewModel
import java.util.Locale

class HomeFragment(private var binding3: FragmentHomeBinding? = null) : Fragment() {
    private val binding get() = binding3!!

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding3 = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        binding3 = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

        initSearchView()

        initRecyckler()

        filmsAdapter.addItems(filmsDataBase)

        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
        })
    }

    private fun initSearchView() {
        binding.searchView
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isEmpty()) {
                        filmsAdapter.addItems(filmsDataBase)
                        return true
                    }
                    val result = filmsDataBase.filter {
                        it.title.lowercase(Locale.getDefault()).contains(
                            newText.lowercase(
                                Locale.getDefault()
                            )
                        )
                    }
                    filmsAdapter.addItems(result)
                    return true
                }
            })
    }

    private fun initRecyckler() {
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })

            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
}