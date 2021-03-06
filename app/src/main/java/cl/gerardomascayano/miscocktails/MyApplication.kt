package cl.gerardomascayano.miscocktails

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}