package co.pacastrillonp.wifimanager.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import co.pacastrillonp.wifimanager.domain.AvailableNetworkService
import co.pacastrillonp.wifimanager.model.AvailableWifiViewModelItem
import co.pacastrillonp.wifimanager.view.AvailableWifiActions
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class WifiViewModel @Inject constructor(
//    private val availableNetworkService: AvailableNetworkService
) : ViewModel(), WifiAvailableEventListener {

    // Inputs
    val loadWifiListInput = PublishSubject.create<Unit>()


    // Outputs
    val wifiAvailableListOutput: Observable<List<AvailableWifiViewModelItem>>


    init {
        //TODO: Investigar como solucionar el status de la red
//        wifiAvailableListOutput = loadWifiListInput
//            .flatMap { availableNetworkService.availableNetworks }
//            .map { availableWifiList ->
//                availableWifiList.map {
//                    AvailableWifiViewModelItem(
//                        it.SSID,
//                        it.isPasspointNetwork,
//                        it.frequency
//                    )
//                }
//            }


        wifiAvailableListOutput = loadWifiListInput.map {
            listOf<AvailableWifiViewModelItem>(
                AvailableWifiViewModelItem("tekus", true, 4),
                AvailableWifiViewModelItem("tekuzeros", false, 3),
                AvailableWifiViewModelItem("tekuzeros", false, 3),
                AvailableWifiViewModelItem("tekuzeros", false, 0),
                AvailableWifiViewModelItem("tekuzeros", false, 2),
                AvailableWifiViewModelItem("tekuzeros", false, 1)
            )
        }
    }

    override fun onClick(availableWifiViewModelItem: AvailableWifiViewModelItem) {

    }
}

interface WifiAvailableEventListener : AvailableWifiActions