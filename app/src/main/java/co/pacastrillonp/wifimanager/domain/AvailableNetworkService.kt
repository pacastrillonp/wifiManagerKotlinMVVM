package co.pacastrillonp.wifimanager.domain

import android.net.wifi.ScanResult
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

interface AvailableNetworkService {
    val availableNetworks: Observable<List<ScanResult>>

}

open class DefaultAvailableNetworkService @Inject constructor(
//    @Named("wifiManagerTool") private val wifiManagerTool: WifiManagerTool
) : AvailableNetworkService {
    override val availableNetworks: Observable<List<ScanResult>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}