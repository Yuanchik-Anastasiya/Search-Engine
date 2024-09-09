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
import com.yuanchik.myapplicationbebe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

private val main_recycler = findViewById<RecyclerView>(R.id.main_recycler)

     val filmsDataBase = listOf(
        Film(
            getString(R.string.title_venom),
            R.drawable.venom,
            getString(R.string.description_venom)
        ),
        Film(
            getString(R.string.title_beekeeper),
            R.drawable.beekeeper,
            getString(R.string.description_beekeeper)
        ),
        Film(
            getString(R.string.title_despicable_me),
            R.drawable.despicable_me4,
            getString(R.string.description_despicable_me)
        ),
        Film(
            getString(R.string.title_alien),
            R.drawable.alien,
            getString(R.string.description_alien)
        ),
        Film(
            getString(R.string.title_puzzle),
            R.drawable.puzzle2,
            getString(R.string.description_puzzle)
        ),
        Film(
            getString(R.string.title_bad_guys),
            R.drawable.the_bad_guys_to_the_end,
            getString(R.string.discription_bad_guys)
        ),
        Film(
            getString(R.string.title_planet_of_the_apes),
            R.drawable.planet_of_the_apes,
            getString(R.string.discription_planet_of_the_apes)
        )
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

        main_recycler.apply {
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