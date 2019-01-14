package vn.linh.androidwifiscanner.adapter

import android.net.wifi.ScanResult
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_wifi.view.*
import vn.linh.androidwifiscanner.R

class WifiAdapter : ListAdapter<ScanResult, WifiAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder.create(p0)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_wifi_name.text = getItem(position).SSID
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wifi,  parent,false))
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : android.support.v7.util.DiffUtil.ItemCallback<ScanResult>() {

            override fun areItemsTheSame(p0: ScanResult, p1: ScanResult): Boolean {
                return false
            }

            override fun areContentsTheSame(p0: ScanResult, p1: ScanResult): Boolean {
                return p0 == p1
            }
        }
    }
}