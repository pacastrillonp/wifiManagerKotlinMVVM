package co.pacastrillonp.wifimanager.view

import androidx.lifecycle.ViewModel
import co.pacastrillonp.wifimanager.di.util.ViewModelKey
import co.pacastrillonp.wifimanager.viewmodel.WifiViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class WifiActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(WifiViewModel::class)
    internal abstract fun bindWifiViewModel(viewModel: WifiViewModel): ViewModel
}