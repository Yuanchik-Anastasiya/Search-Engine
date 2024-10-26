package com.yuanchik.myapplicationbebe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplicationbebe.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment(private var binding3: FragmentHomeBinding? = null) : Fragment() {
    private val binding get() = binding3!!

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val filmsDataBase = listOf(
        Film(
            "Веном: Последний танец",
            R.drawable.venom,
            "Эдди и Веном в бегах. Преследуемые обоими мирами и с приближающейся сетью, дуэт вынужден принять сокрушительное решение, которое опустит занавес над последним танцем Венома и Эдди.", 10f
        ),
        Film(
            "Пчеловод",
            R.drawable.beekeeper,
            "Добросердечная домовладелица совершает самоубийство, став жертвой фишинговой аферы, что заставляет бывшего сотрудника. Пасечника Адама Клея начать жестокую кампанию по отмщению виновным.", 5.3f
        ),
        Film(
            "Гадкий я 4",
            R.drawable.despicable_me4,
            "Грю, Люси, Марго, Эдит и Агнес приветствуют нового члена семьи, Грю-младшего, который намерен мучить своего отца.", 9.3f
        ),
        Film(
            "Чужой: Ромул",
            R.drawable.alien,
            "Исследуя недра заброшенной космической станции, группа молодых космических колонистов сталкивается лицом к лицу с самой ужасающей формой жизни во Вселенной.", 9.8f
        ),
        Film(
            "Головоломка 2",
            R.drawable.puzzle2,
            "Продолжение, в котором Райли вступает в период полового созревания и в результате испытывает совершенно новые, более сложные эмоции.", 6.9f
        ),
        Film(
            "Плохие парни до конца",
            R.drawable.the_bad_guys_to_the_end,
            "Когда их покойный капитан полиции оказывается связанным с наркокартелями, остроумные копы Майами Майк Лоури и Маркус Бернетт отправляются на опасную миссию, чтобы очистить его имя.", 8.7f
        ),
        Film(
            "Планета обезьян: Новое царство",
            R.drawable.planet_of_the_apes,
            "Спустя много лет после правления Цезаря молодая обезьяна отправляется в путешествие, которое заставит ее подвергнуть сомнению все, чему ее учили о прошлом, и сделать выбор, который определит будущее как обезьян, так и людей.", 10f
        )
    )


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