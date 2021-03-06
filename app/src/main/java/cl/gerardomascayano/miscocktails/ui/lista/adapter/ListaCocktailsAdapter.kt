package cl.gerardomascayano.miscocktails.ui.lista.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.model.Cocktail
import cl.gerardomascayano.miscocktails.databinding.ItemCocktailBinding
import cl.gerardomascayano.miscocktails.util.extension.inflate
import com.bumptech.glide.Glide

class ListaCocktailsAdapter(private val listCocktails: List<Cocktail>, private val cocktailItemListener: OnCocktailItemClickListener) :
    RecyclerView.Adapter<ListaCocktailsAdapter.CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder = CocktailViewHolder(
        ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listCocktails.size
    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) = holder.bindData(listCocktails[position])

    interface OnCocktailItemClickListener {
        fun onCocktailItemClickListener(view: View, cocktail: Cocktail)
    }

    inner class CocktailViewHolder(private val viewBinding: ItemCocktailBinding) : RecyclerView.ViewHolder(viewBinding.root), View.OnClickListener {

        fun bindData(cocktail: Cocktail) {
            viewBinding.ivCocktailImage.transitionName = adapterPosition.toString()
            viewBinding.tvCocktailName.text = cocktail.nombre
            viewBinding.tvCocktailCantidad.text = "${cocktail.cantidad}${cocktail.cantidadUm}"
            Glide
                .with(viewBinding.ivCocktailImage)
                .load(cocktail.imagen)
                .centerCrop()
                .into(viewBinding.ivCocktailImage)
            viewBinding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) = cocktailItemListener.onCocktailItemClickListener(
            viewBinding.ivCocktailImage,
            listCocktails[adapterPosition]
        )
    }
}