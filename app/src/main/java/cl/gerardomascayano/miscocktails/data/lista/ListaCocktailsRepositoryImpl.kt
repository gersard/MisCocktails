package cl.gerardomascayano.miscocktails.data.lista

import cl.gerardomascayano.miscocktails.data.model.Cocktail
import cl.gerardomascayano.miscocktails.util.FirestoreConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class ListaCocktailsRepositoryImpl : ListaCocktailsRepository {

    override suspend fun getCocktails(): MutableList<Cocktail>? {
        return try {
            val snapshot = FirebaseFirestore.getInstance().collection(FirestoreConstants.COLLECTION_COCKTAILS).get().await()
            snapshot.toObjects(Cocktail::class.java)
        } catch (e: FirebaseFirestoreException) {
            Timber.e(e)
            null
        }
    }
}