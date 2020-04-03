package cl.gerardomascayano.miscocktails.ui.lista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.data.lista.ListaCocktailsRepositoryImpl
import cl.gerardomascayano.miscocktails.model.Cocktail
import cl.gerardomascayano.miscocktails.model.ListaCocktailsEvent
import cl.gerardomascayano.miscocktails.databinding.ActivityListaCocktailsBinding
import cl.gerardomascayano.miscocktails.domain.lista.ListaCocktailsUseCaseImpl
import cl.gerardomascayano.miscocktails.ui.detalle.DetalleCocktailActivity
import cl.gerardomascayano.miscocktails.ui.lista.adapter.ListaCocktailsAdapter
import cl.gerardomascayano.miscocktails.ui.lista.viewmodel.ListaCocktailsViewModel
import cl.gerardomascayano.miscocktails.ui.lista.viewmodel.ListaCocktailsViewModelFactory
import cl.gerardomascayano.miscocktails.ui.mantenedor.MantenedorCocktailActivity
import cl.gerardomascayano.miscocktails.util.extension.exhaustive
import cl.gerardomascayano.miscocktails.util.extension.gone
import cl.gerardomascayano.miscocktails.util.extension.visible
import com.google.gson.Gson

class ListaCocktailsActivity : AppCompatActivity(), ListaCocktailsAdapter.OnCocktailItemClickListener {

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
        viewBind.fabAddCocktail.setOnClickListener { startActivity(Intent(this, MantenedorCocktailActivity::class.java)) }
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
        viewBind.rvListaCocktails.adapter = ListaCocktailsAdapter(listCocktails, this)
    }

    override fun onCocktailItemClickListener(view: View, cocktail: Cocktail) {
        val intent = Intent(this, DetalleCocktailActivity::class.java)
        intent.putExtra("cocktail", Gson().toJson(cocktail))
        val transitionName = ViewCompat.getTransitionName(view)!!
        intent.putExtra("transition_name", transitionName)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, transitionName)

        startActivity(intent, options.toBundle())
    }
}
