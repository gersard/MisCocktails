package cl.gerardomascayano.miscocktails.ui.lista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gerardomascayano.miscocktails.data.lista.ListaCocktailsRepositoryImpl
import cl.gerardomascayano.miscocktails.model.Cocktail
import cl.gerardomascayano.miscocktails.model.ListaCocktailsEvent
import cl.gerardomascayano.miscocktails.databinding.ActivityListaCocktailsBinding
import cl.gerardomascayano.miscocktails.domain.lista.ListaCocktailsUseCaseImpl
import cl.gerardomascayano.miscocktails.ui.lista.adapter.ListaCocktailsAdapter
import cl.gerardomascayano.miscocktails.ui.lista.viewmodel.ListaCocktailsViewModel
import cl.gerardomascayano.miscocktails.ui.lista.viewmodel.ListaCocktailsViewModelFactory
import cl.gerardomascayano.miscocktails.util.extension.exhaustive
import cl.gerardomascayano.miscocktails.util.extension.gone
import cl.gerardomascayano.miscocktails.util.extension.visible

class ListaCocktailsActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityListaCocktailsBinding
    private val listCocktailsViewModel: ListaCocktailsViewModel by lazy {
        ViewModelProvider(
            this,
            ListaCocktailsViewModelFactory(
                ListaCocktailsUseCaseImpl(
                    ListaCocktailsRepositoryImpl()
                )
            )
        ).get(ListaCocktailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityListaCocktailsBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        listCocktailsViewModel.getCocktails()
        observeListacocktailsEvent()
    }

    private fun observeListacocktailsEvent() {
        listCocktailsViewModel.listaCocktailsEvent.observe(this, Observer { event ->
            when (event) {
                is ListaCocktailsEvent.Loading -> if (event.isLoading) viewBind.progressBar.visible() else viewBind.progressBar.gone()
                is ListaCocktailsEvent.Sucess -> configureListaCocktails(event.listCocktails)
                is ListaCocktailsEvent.Failure -> Toast.makeText(this, event.message, Toast.LENGTH_LONG).show()
            }.exhaustive
        })
    }

    private fun configureListaCocktails(listCocktails: List<Cocktail>) {
        viewBind.rvListaCocktails.setHasFixedSize(true)
        viewBind.rvListaCocktails.layoutManager = LinearLayoutManager(this)
        viewBind.rvListaCocktails.adapter = ListaCocktailsAdapter(listCocktails)
    }
}
