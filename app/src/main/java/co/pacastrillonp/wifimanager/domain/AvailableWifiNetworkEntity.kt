package co.pacastrillonp.wifimanager.domain

import android.net.wifi.ScanResult

data class AvailableWifiNetworkEntity(
    val scanResults: List<ScanResult>,
    val ssidNetworkConnect: String
)