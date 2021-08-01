package vn.linh.androidwifiscanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import vn.linh.androidwifiscanner.adapter.WifiAdapter

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var wifiManager: WifiManager
    lateinit var adapter: WifiAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_scan.setOnClickListener {
            adapter.submitList(arrayListOf())
            scanWifiNetworks()
        }

        check_full_info.setOnClickListener {
            adapter.showFullInfo = check_full_info.isChecked
            adapter.notifyDataSetChanged()
        }

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        adapter = WifiAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = adapter
    }

    private fun scanWifiNetworks() {
        // for testing purpose, clear the list first
        adapter.submitList(listOf())

        registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        } else {
            Toast.makeText(this, "SCANNING", Toast.LENGTH_SHORT).show()
        }
    }

    private fun scanFailure() {
        Toast.makeText(this, "SCAN FAILURE", Toast.LENGTH_SHORT).show()
    }

    private var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive " + wifiManager.scanResults.size)
            Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show()
            unregisterReceiver(this)
            adapter.submitList(wifiManager.scanResults)
        }
    }
}
