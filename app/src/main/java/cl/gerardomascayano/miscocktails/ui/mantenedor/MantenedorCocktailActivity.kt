package cl.gerardomascayano.miscocktails.ui.mantenedor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gerardomascayano.miscocktails.databinding.ActivityMantenedorCocktailBinding

class MantenedorCocktailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMantenedorCocktailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMantenedorCocktailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}
