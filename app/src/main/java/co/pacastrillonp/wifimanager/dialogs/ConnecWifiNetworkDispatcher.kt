package co.pacastrillonp.wifimanager.dialogs

import androidx.fragment.app.FragmentActivity
import co.pacastrillonp.wifimanager.view.ConnectWifiDialogFragment
import javax.inject.Inject

class ConnecWifiNetworkDispatcher @Inject constructor() {

    fun openConnectWifiNetworkDialog(activity: FragmentActivity) {
        val dialog = ConnectWifiDialogFragment()
        dialog.show(activity.supportFragmentManager, DIALOG_CONNECT_WIFI_NETWORK)
    }

    companion object {
        const val DIALOG_CONNECT_WIFI_NETWORK = "dialog_connect_wifi_network"
    }
}