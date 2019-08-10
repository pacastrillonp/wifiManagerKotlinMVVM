package co.pacastrillonp.wifimanager.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.pacastrillonp.wifimanager.R
import co.pacastrillonp.wifimanager.viewmodel.ConnectWifiViewModel

class ConnectWifiFragment : Fragment() {

    companion object {
        fun newInstance() = ConnectWifiFragment()
    }

    private lateinit var viewModel: ConnectWifiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.connect_wifi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConnectWifiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
