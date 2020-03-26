package cl.gerardomascayano.miscocktails.ui.lista.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.data.model.Cocktail
import cl.gerardomascayano.miscocktails.util.extension.inflate

class ListaCocktailsAdapter(val listCocktails: List<Cocktail>) : RecyclerView.Adapter<ListaCocktailsAdapter.CocktailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder = CocktailViewHolder(parent.inflate(R.layout.item_cocktail))
    override fun getItemCount(): Int = listCocktails.size


    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}