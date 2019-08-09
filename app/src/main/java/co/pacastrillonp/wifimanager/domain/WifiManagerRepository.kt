package co.pacastrillonp.wifimanager.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface WifiManagerRepository {
    fun getAvailableWifiNetworks(): Observable<AvailableWifiNetworkEntity>
    fun connectToNetwork(wifiConfig: WifiConfiguration)
}

class DefaultMediaRepository @Inject constructor(context: Context) : WifiManagerRepository {


    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getAvailableWifiNetworks(): Observable<AvailableWifiNetworkEntity> {
        val loadWifiListInput = PublishSubject.create<AvailableWifiNetworkEntity>()
        loadWifiListInput.onNext(AvailableWifiNetworkEntity(wifiManager.scanResults, ssidNetworkConnected()))
        return loadWifiListInput
    }

    override fun connectToNetwork(wifiConfig: WifiConfiguration) {
        wifiManager.addNetwork(wifiConfig)
    }

    private fun ssidNetworkConnected(): String {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        if (isConnected) {
            val wifiInfo = wifiManager.connectionInfo
            return wifiInfo.ssid
        }
        return ""
    }
}




