package co.pacastrillonp.wifimanager.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.pacastrillonp.wifimanager.R
import co.pacastrillonp.wifimanager.databinding.ActivityWifiBinding
import co.pacastrillonp.wifimanager.di.util.viewModelProvider
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


    private val myPermissionsAccessCoarseLocation = 1

    private lateinit var  wifiManager: WifiManager
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



         wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), myPermissionsAccessCoarseLocation
            )
        } else {
            wifiViewModel.loadWifiListInput.onNext(Unit)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            myPermissionsAccessCoarseLocation -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    wifiManager.scanResults
                    wifiViewModel.loadWifiListInput.onNext(Unit)
                } else {
                    return
                }
                return
            }
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
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



