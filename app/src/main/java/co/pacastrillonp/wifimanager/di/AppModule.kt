package co.pacastrillonp.wifimanager.di

import android.content.Context
import co.pacastrillonp.wifimanager.MainApplication
import co.pacastrillonp.wifimanager.domain.AvailableNetworkService
import co.pacastrillonp.wifimanager.domain.DefaultAvailableNetworkService
import co.pacastrillonp.wifimanager.domain.WifiManagerTool
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(application: MainApplication): Context = application.applicationContext
//
//    @Singleton
//    @Provides
//    fun provideAvailableNetworkService(
//        @Named("wifiManagerTool") wifiManagerTool: WifiManagerTool
//    ): AvailableNetworkService {
//        return DefaultAvailableNetworkService(wifiManagerTool)
//    }

}