package co.pacastrillonp.wifimanager.model

data class AvailableWifiViewModelItem(
    val ssid: String,
    val status: Boolean,
    val signal: Int
)