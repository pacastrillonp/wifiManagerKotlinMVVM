package co.pacastrillonp.wifimanager.di

import android.content.Context
import co.pacastrillonp.wifimanager.MainApplication
import co.pacastrillonp.wifimanager.domain.DefaultAvailableNetworkService
import co.pacastrillonp.wifimanager.domain.DefaultMediaRepository
import co.pacastrillonp.wifimanager.domain.WifiManagerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(application: MainApplication): Context = application.applicationContext

    @Singleton
    @Provides
    @Named("wifiManagerRepository")
    fun provideWifiManagerRepository(
        context: Context
    ): WifiManagerRepository {
        return DefaultMediaRepository(context)
    }


    @Singleton
    @Provides
    fun provideAvailableNetworkService(
        @Named("wifiManagerRepository") wifiManagerRepository: WifiManagerRepository
    ): DefaultAvailableNetworkService {
        return DefaultAvailableNetworkService(wifiManagerRepository)
    }

}