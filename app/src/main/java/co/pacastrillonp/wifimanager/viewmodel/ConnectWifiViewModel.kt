package co.pacastrillonp.wifimanager.viewmodel

import androidx.lifecycle.ViewModel;
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ConnectWifiViewModel @Inject constructor(
) :ViewModel() {


    // Inputs
    val loadNetworkDataInput = PublishSubject.create<Unit>()


}
