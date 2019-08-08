package co.pacastrillonp.wifimanager.domain

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager

class WifiManagerTool(context: Context) {

    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager


    fun getAvailableWifiNetworks(): List<ScanResult> {
        return wifiManager.scanResults
    }
}