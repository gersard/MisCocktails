package cl.gerardomascayano.miscocktails.ui.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.databinding.ActivityDetalleCocktailBinding
import cl.gerardomascayano.miscocktails.model.Cocktail
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
        Glide.with(this)
            .load(viewModel.cocktail!!.imagen)
            .centerCrop()
            .into(viewBinding.ivCocktailImage)
    }
}
