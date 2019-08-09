package co.pacastrillonp.wifimanager.viewmodel

import androidx.lifecycle.ViewModel
import co.pacastrillonp.wifimanager.domain.AvailableNetworkService
import co.pacastrillonp.wifimanager.model.AvailableWifiViewModelItem
import co.pacastrillonp.wifimanager.view.AvailableWifiActions
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class WifiViewModel @Inject constructor(
    availableNetworkService: AvailableNetworkService
) : ViewModel(), WifiAvailableEventListener {

    // Inputs
    val loadWifiListInput = PublishSubject.create<Unit>()


    // Outputs
    val wifiAvailableListOutput: Observable<List<AvailableWifiViewModelItem>>


    init {
        //TODO: Investigar como solucionar el status de la red

        wifiAvailableListOutput = loadWifiListInput
            .flatMap { availableNetworkService.availableNetworks }
            .map { availableWifiNetworkEntity ->
                availableWifiNetworkEntity.scanResults.map {
                    val status: Boolean = availableWifiNetworkEntity.ssidNetworkConnect == it.SSID
                    AvailableWifiViewModelItem(it.SSID, status, it.level)
                }
            }

//        wifiAvailableListOutput = loadWifiListInput.map {
//            listOf<AvailableWifiViewModelItem>(
//                AvailableWifiViewModelItem("tekus", true, 4),
//                AvailableWifiViewModelItem("tekuzeros", false, 3),
//                AvailableWifiViewModelItem("tekuzeros", false, 3),
//                AvailableWifiViewModelItem("tekuzeros", false, 0),
//                AvailableWifiViewModelItem("tekuzeros", false, 2),
//                AvailableWifiViewModelItem("tekuzeros", false, 1)
//            )
//        }
    }

    override fun onClick(availableWifiViewModelItem: AvailableWifiViewModelItem) {

    }
}

interface WifiAvailableEventListener : AvailableWifiActions