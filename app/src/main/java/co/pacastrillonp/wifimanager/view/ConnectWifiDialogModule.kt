package co.pacastrillonp.wifimanager.view

import androidx.lifecycle.ViewModel
import co.pacastrillonp.wifimanager.di.util.ChildFragmentScoped
import co.pacastrillonp.wifimanager.di.util.ViewModelKey
import co.pacastrillonp.wifimanager.viewmodel.ConnectWifiViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ConnectWifiDialogModule {
    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeConnectWifiDialogFragment(): ConnectWifiDialogFragment

    @Binds
    @IntoMap
    @ViewModelKey(ConnectWifiViewModel::class)
    internal abstract fun bindConnectWifiViewModel(viewModel: ConnectWifiViewModel): ViewModel

}