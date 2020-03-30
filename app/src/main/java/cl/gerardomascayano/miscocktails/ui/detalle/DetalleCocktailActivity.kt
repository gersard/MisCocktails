package cl.gerardomascayano.miscocktails.ui.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.databinding.ActivityDetalleCocktailBinding
import cl.gerardomascayano.miscocktails.model.Cocktail
import cl.gerardomascayano.miscocktails.ui.detalle.adapter.IngredientesAdapter
import cl.gerardomascayano.miscocktails.ui.detalle.viewmodel.DetalleCocktailViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson

class DetalleCocktailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDetalleCocktailBinding
    private val viewModel: DetalleCocktailViewModel by lazy { ViewModelProvider(this).get(DetalleCocktailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetalleCocktailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.cocktail = Gson().fromJson(intent.getStringExtra("cocktail"), Cocktail::class.java)
        configureUi()
    }

    private fun configureUi() {
        viewModel.cocktail?.run {
            // Imagen
            Glide.with(this@DetalleCocktailActivity)
                .load(imagen)
                .centerCrop()
                .into(viewBinding.ivCocktailImage)

            viewBinding.ctlDetalleCocktail.title = nombre
            viewBinding.tvPreparacionCocktail.text = preparacion
            viewBinding.tvNotasCocktail.text = notas

            // Lista ingredientes
            ingredientes?.let {
                viewBinding.rvIngredientesCocktail.setHasFixedSize(true)
                viewBinding.rvIngredientesCocktail.layoutManager = LinearLayoutManager(this@DetalleCocktailActivity)
                viewBinding.rvIngredientesCocktail.adapter = IngredientesAdapter(ingredientes)
            }
        }

    }
}
