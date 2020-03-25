package cl.gerardomascayano.miscocktails.data.lista

import cl.gerardomascayano.miscocktails.data.model.Cocktail
import cl.gerardomascayano.miscocktails.util.FirestoreConstants
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class ListaCocktailsRepositoryImpl : ListaCocktailsRepository {

    override fun getCocktails() {
        FirebaseFirestore.getInstance().collection(FirestoreConstants.COLLECTION_COCKTAILS).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val listCocktails = it.result?.toObjects(Cocktail::class.java)
                listCocktails?.forEach { cocktail ->
                    Timber.d("Nombre: ${cocktail.nombre}")
                }
            } else {
                Timber.e("Error: ${it.exception}")
            }
        }
    }
}