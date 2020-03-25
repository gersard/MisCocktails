package cl.gerardomascayano.miscocktails.data.lista

import cl.gerardomascayano.miscocktails.util.FirestoreConstants
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class ListaCocktailsRepositoryImpl : ListaCocktailsRepository {

    override fun getCocktails() {
        FirebaseFirestore.getInstance().collection(FirestoreConstants.COLLECTION_COCKTAILS).get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.forEach { document ->
                    Timber.d("ID: ${document.id} : ${document.data}")
                }
            } else {
                Timber.e("Error: ${it.exception}")
            }
        }
    }
}