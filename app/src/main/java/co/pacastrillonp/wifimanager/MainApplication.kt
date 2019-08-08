package co.pacastrillonp.wifimanager

import co.pacastrillonp.wifimanager.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)
}
