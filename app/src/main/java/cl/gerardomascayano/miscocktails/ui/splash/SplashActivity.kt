package cl.gerardomascayano.miscocktails.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.ui.lista.ListaCocktailsActivity
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(getString(R.string.auth_email), getString(R.string.auth_pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, ListaCocktailsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Timber.d("Error: ${it.exception?.localizedMessage}")
            }
        }
    }
}
