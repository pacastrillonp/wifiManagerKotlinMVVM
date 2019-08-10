package co.pacastrillonp.wifimanager.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.pacastrillonp.wifimanager.databinding.ConnectWifiDialogFragmentBinding
import co.pacastrillonp.wifimanager.di.util.viewModelProvider
import co.pacastrillonp.wifimanager.util.rx
import co.pacastrillonp.wifimanager.util.subscribeOnUi
import co.pacastrillonp.wifimanager.util.tap
import co.pacastrillonp.wifimanager.viewmodel.ConnectWifiViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerDialogFragment
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ConnectWifiDialogFragment : DaggerDialogFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ConnectWifiDialogFragmentBinding

    private lateinit var viewModel: ConnectWifiViewModel

    private val disposable = CompositeDisposable()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = ConnectWifiDialogFragmentBinding.inflate(inflater,container,false)
        bindViewModel()
        viewModel.loadNetworkDataInput.onNext(Unit)
        return binding.root
    }



    private fun bindViewModel() {
        binding.connectButton.rx.tap.subscribeOnUi(disposable){
            //TODO: enlazar con view model
        }
        binding.cancelButton.rx.tap.subscribeOnUi(disposable) { dismiss() }
    }

    companion object {
        fun newInstance(): ConnectWifiDialogFragment {
            return ConnectWifiDialogFragment()
        }
    }

}
