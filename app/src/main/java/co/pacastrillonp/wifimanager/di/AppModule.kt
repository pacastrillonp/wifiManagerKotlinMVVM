package co.pacastrillonp.wifimanager.di

import android.content.Context
import co.pacastrillonp.wifimanager.MainApplication
import co.pacastrillonp.wifimanager.domain.*
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
    @Named("wifiRepository")
    fun provideWifiRepository(
        context: Context
    ): WifiRepository {
        return DefaultWifiRepository(context)
    }

    @Singleton
    @Provides
    fun provideAvailableNetworkService(
        @Named("wifiRepository") wifiRepository: WifiRepository
    ): AvailableNetworkService {
        return DefaultAvailableNetworkService(wifiRepository)
    }

}