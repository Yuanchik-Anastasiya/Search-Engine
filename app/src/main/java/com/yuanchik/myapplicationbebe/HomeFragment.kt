package com.yuanchik.myapplicationbebe

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplicationbebe.databinding.FragmentDetailsBinding
import com.yuanchik.myapplicationbebe.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding_: FragmentHomeBinding? = null
    private val binding get() = binding_!!

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    val filmsDataBase = listOf(
        Film(
            "Веном: Последний танец",
            R.drawable.venom,
            "Эдди и Веном в бегах. Преследуемые обоими мирами и с приближающейся сетью, дуэт вынужден принять сокрушительное решение, которое опустит занавес над последним танцем Венома и Эдди."
        ),
        Film(
            "Пчеловод",
            R.drawable.beekeeper,
            "Добросердечная домовладелица совершает самоубийство, став жертвой фишинговой аферы, что заставляет бывшего сотрудника. Пасечника Адама Клея начать жестокую кампанию по отмщению виновным."
        ),
        Film(
            "Гадкий я 4",
            R.drawable.despicable_me4,
            "Грю, Люси, Марго, Эдит и Агнес приветствуют нового члена семьи, Грю-младшего, который намерен мучить своего отца."
        ),
        Film(
            "Чужой: Ромул",
            R.drawable.alien,
            "Исследуя недра заброшенной космической станции, группа молодых космических колонистов сталкивается лицом к лицу с самой ужасающей формой жизни во Вселенной."
        ),
        Film(
            "Головоломка 2",
            R.drawable.puzzle2,
            "Продолжение, в котором Райли вступает в период полового созревания и в результате испытывает совершенно новые, более сложные эмоции."
        ),
        Film(
            "Плохие парни до конца",
            R.drawable.the_bad_guys_to_the_end,
            "Когда их покойный капитан полиции оказывается связанным с наркокартелями, остроумные копы Майами Майк Лоури и Маркус Бернетт отправляются на опасную миссию, чтобы очистить его имя."
        ),
        Film(
            "Планета обезьян: Новое царство",
            R.drawable.planet_of_the_apes,
            "Спустя много лет после правления Цезаря молодая обезьяна отправляется в путешествие, которое заставит ее подвергнуть сомнению все, чему ее учили о прошлом, и сделать выбор, который определит будущее как обезьян, так и людей."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding_ = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecycler?.apply {
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

        filmsAdapter.addItems(filmsDataBase)
    }
}