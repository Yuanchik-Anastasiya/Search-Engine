package com.yuanchik.myapplicationbebe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuanchik.myapplicationbebe.R.string
import com.yuanchik.myapplicationbebe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

     val filmsDataBase = listOf(
         Film("Веном: Последний танец", R.drawable.venom, "Эдди и Веном в бегах. Преследуемые обоими мирами и с приближающейся сетью, дуэт вынужден принять сокрушительное решение, которое опустит занавес над последним танцем Венома и Эдди."),
         Film("Пчеловод", R.drawable.beekeeper, "Добросердечная домовладелица совершает самоубийство, став жертвой фишинговой аферы, что заставляет бывшего сотрудника. Пасечника Адама Клея начать жестокую кампанию по отмщению виновным."),
         Film("Гадкий я 4", R.drawable.despicable_me4, "Грю, Люси, Марго, Эдит и Агнес приветствуют нового члена семьи, Грю-младшего, который намерен мучить своего отца."),
         Film("Чужой: Ромул", R.drawable.alien, "Исследуя недра заброшенной космической станции, группа молодых космических колонистов сталкивается лицом к лицу с самой ужасающей формой жизни во Вселенной."),
         Film("Головоломка 2", R.drawable.puzzle2, "Продолжение, в котором Райли вступает в период полового созревания и в результате испытывает совершенно новые, более сложные эмоции."),
         Film("Плохие парни до конца", R.drawable.the_bad_guys_to_the_end, "Когда их покойный капитан полиции оказывается связанным с наркокартелями, остроумные копы Майами Майк Лоури и Маркус Бернетт отправляются на опасную миссию, чтобы очистить его имя."),
         Film("Планета обезьян: Новое царство", R.drawable.planet_of_the_apes, "Спустя много лет после правления Цезаря молодая обезьяна отправляется в путешествие, которое заставит ее подвергнуть сомнению все, чему ее учили о прошлом, и сделать выбор, который определит будущее как обезьян, так и людей.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initMenuButtons()

        binding.mainRecycler?.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    val bundle = Bundle()
                    bundle.putParcelable("film", film)
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })

            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }

        filmsAdapter.addItems(filmsDataBase)

    }

    private fun initMenuButtons() {
        binding.topAppBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigation?.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}