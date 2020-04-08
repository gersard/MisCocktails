package cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.miscocktails.databinding.ItemIngredienteBinding
import cl.gerardomascayano.miscocktails.model.Ingrediente

class IngredienteMantenedorAdapter(private val listIngredientes: List<Ingrediente>) :
    RecyclerView.Adapter<IngredienteMantenedorAdapter.IngredienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteViewHolder = IngredienteViewHolder(
        ItemIngredienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listIngredientes.size
    override fun onBindViewHolder(holder: IngredienteViewHolder, position: Int) = holder.bindData(listIngredientes[position])


    inner class IngredienteViewHolder(private val viewBinding: ItemIngredienteBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(ingrediente: Ingrediente) {
            viewBinding.tvCantidadIngredienteCocktail.text = "${ingrediente.cantidad} ${ingrediente.cantidadUm}"
            viewBinding.tvNombreIngredienteCocktail.text = ingrediente.nombre
        }

    }

}