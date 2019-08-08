package co.pacastrillonp.wifimanager.di

import co.pacastrillonp.wifimanager.MainApplication
import co.pacastrillonp.wifimanager.di.util.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}