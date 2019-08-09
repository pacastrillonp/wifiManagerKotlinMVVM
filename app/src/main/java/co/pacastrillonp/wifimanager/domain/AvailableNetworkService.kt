package co.pacastrillonp.wifimanager.domain

import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface AvailableNetworkService {
    val availableNetworks: Observable<AvailableWifiNetworkEntity>

}

@Singleton
open class DefaultAvailableNetworkService @Inject constructor(
    @Named("wifiManagerRepository") private val wifiManagerRepository: WifiManagerRepository
) : AvailableNetworkService {
    override val availableNetworks: Observable<AvailableWifiNetworkEntity>
        get() = wifiManagerRepository.getAvailableWifiNetworks()

}