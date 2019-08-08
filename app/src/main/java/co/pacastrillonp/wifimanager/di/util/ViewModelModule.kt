package co.pacastrillonp.wifimanager.di.util

import androidx.lifecycle.ViewModelProvider
import co.pacastrillonp.wifimanager.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}