package co.pacastrillonp.wifimanager.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.pacastrillonp.wifimanager.R
import co.pacastrillonp.wifimanager.databinding.ItemAvailableNetworkBinding
import co.pacastrillonp.wifimanager.model.AvailableWifiViewModelItem
import co.pacastrillonp.wifimanager.viewmodel.WifiAvailableEventListener

class AvailableWifiAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val wifWifiAvailableEventListener: WifiAvailableEventListener
) : ListAdapter<AvailableWifiViewModelItem, AvailableWifiViewHolder>(AvailableWifiDiif) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableWifiViewHolder {
        val binding = ItemAvailableNetworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvailableWifiViewHolder(binding, lifecycleOwner, wifWifiAvailableEventListener)
    }

    override fun onBindViewHolder(wifiViewHolder: AvailableWifiViewHolder, position: Int) {
        wifiViewHolder.bind(getItem(position))
    }
}

class AvailableWifiViewHolder(
    private val binding: ItemAvailableNetworkBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val wifWifiAvailableEventListener: WifiAvailableEventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AvailableWifiViewModelItem) {
        binding.apply {
            availableWifi = item
            lifecycleOwner = this@AvailableWifiViewHolder.lifecycleOwner
            listener = wifWifiAvailableEventListener
            executePendingBindings()
        }
    }
}


@BindingAdapter("srcStatusIcon")
fun setStatusImageResource(imageView: ImageView, networkStatus: Boolean) {
    imageView.apply {
        when (networkStatus) {
            true -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_check_black_18))
            false -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_clear_black_18))
        }
    }
}

@BindingAdapter("srcSignalIcon")
fun setSignalImageResource(imageView: ImageView, signalValue: Int) {
    imageView.apply {
        when (signalValue) {
            0 -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_signal_wifi_0_bar_black_18))
            1 -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_signal_wifi_1_bar_black_18))
            2 -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_signal_wifi_2_bar_black_18))
            3 -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_signal_wifi_3_bar_black_18))
            4 -> setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_signal_wifi_4_bar_black_18))
        }
    }
}


object AvailableWifiDiif : DiffUtil.ItemCallback<AvailableWifiViewModelItem>() {

    override fun areItemsTheSame(
        oldItem: AvailableWifiViewModelItem,
        newItem: AvailableWifiViewModelItem
    ): Boolean {
        return oldItem.ssid == newItem.ssid
    }

    override fun areContentsTheSame(
        oldItem: AvailableWifiViewModelItem,
        newItem: AvailableWifiViewModelItem
    ): Boolean {
        return oldItem == newItem
    }

}