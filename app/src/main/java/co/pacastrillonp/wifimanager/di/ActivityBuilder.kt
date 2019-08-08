package co.pacastrillonp.wifimanager.di


import co.pacastrillonp.wifimanager.di.util.ActivityScoped
import co.pacastrillonp.wifimanager.view.WifiActivity
import co.pacastrillonp.wifimanager.view.WifiActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [WifiActivityModule::class])
    internal abstract fun WifiActivity(): WifiActivity
}