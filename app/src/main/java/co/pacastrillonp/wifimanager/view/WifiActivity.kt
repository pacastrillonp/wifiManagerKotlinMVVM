package co.pacastrillonp.wifimanager.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.pacastrillonp.wifimanager.R
import co.pacastrillonp.wifimanager.databinding.ActivityWifiBinding
import co.pacastrillonp.wifimanager.di.util.viewModelProvider
import co.pacastrillonp.wifimanager.domain.WifiManagerTool
import co.pacastrillonp.wifimanager.viewmodel.WifiViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class WifiActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var wifiViewModel: WifiViewModel
    private lateinit var availableWifiAdapter: AvailableWifiAdapter

    private val disposable = CompositeDisposable()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wifiViewModel = viewModelProvider(viewModelFactory)

        val binding = DataBindingUtil.setContentView<ActivityWifiBinding>(
            this, R.layout.activity_wifi
        ).apply {
            lifecycleOwner = this@WifiActivity
        }
        availableWifiAdapter = AvailableWifiAdapter(this, wifiViewModel)
        binding.recyclerView.apply {
            adapter = availableWifiAdapter
        }
        bindViewModel()
        wifiViewModel.loadWifiListInput.onNext(Unit)


        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wifiScanReceiver = object : BroadcastReceiver() {

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    wifiManager.scanResults
                } else {
                    wifiManager.scanResults
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        this.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()

        if (!success) {
            wifiManager.scanResults

        }

        if (wifiManager.isScanAlwaysAvailable) {

        }

        if (wifiManager.scanResults.isNotEmpty()) {
            print(wifiManager.scanResults.toString())
        }


        val wifiManagerTool = WifiManagerTool(this)
        wifiManagerTool.getAvailableWifiNetworks()
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    override fun onResume() {
//        registerReceiver(receiverWifi,  IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        super.onResume()
    }

    //    override fun onBackPressed() {
//        val intent = Intent(this, Other::class.java).apply {
//            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        }
//        startActivity(intent)
//        finish()
//    }

    private fun bindViewModel() {
        wifiViewModel.wifiAvailableListOutput
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                availableWifiAdapter.submitList(it)
            }
            .addTo(disposable)
    }
}



