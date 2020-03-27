package cl.gerardomascayano.miscocktails.ui.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.databinding.ActivityDetalleCocktailBinding

class DetalleCocktailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDetalleCocktailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetalleCocktailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}
