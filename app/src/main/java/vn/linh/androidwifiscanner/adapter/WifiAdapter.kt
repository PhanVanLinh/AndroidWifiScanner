package vn.linh.androidwifiscanner.adapter

import android.net.wifi.ScanResult
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_wifi.view.*
import vn.linh.androidwifiscanner.R

class WifiAdapter : ListAdapter<ScanResult, WifiAdapter.ViewHolder>(DIFF_UTIL) {

    var showFullInfo = true

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder.create(p0)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val result = getItem(position)
        if (showFullInfo) {
            viewHolder.itemView.text_wifi_name.text =
                "SSID: " + result.SSID + "\n" +
                        "BSSID: " + result.BSSID + "\n" +
                        "capabilities: " + result.capabilities + "\n" +
                        "channelWidth: " + result.channelWidth + "\n" +
                        "frequency: " + result.frequency + "\n" +
                        "is80211mcResponder: " + result.is80211mcResponder + "\n" +
                        "isPasspointNetwork: " + result.isPasspointNetwork + "\n" +
                        "level: " + result.level + "\n"
        } else {
            viewHolder.itemView.text_wifi_name.text = "SSID: " + result.SSID + "\n"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_wifi, parent, false)
                )
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ScanResult>() {

            override fun areItemsTheSame(p0: ScanResult, p1: ScanResult): Boolean {
                return false
            }

            override fun areContentsTheSame(p0: ScanResult, p1: ScanResult): Boolean {
                return p0.equals(p1)
            }
        }
    }
}