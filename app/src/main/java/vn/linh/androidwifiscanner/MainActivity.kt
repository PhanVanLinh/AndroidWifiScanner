package vn.linh.androidwifiscanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
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
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        adapter = WifiAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun scanWifiNetworks() {
        registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        }
        Toast.makeText(this, "Scanning", Toast.LENGTH_SHORT).show()
    }

    private fun scanFailure() {
        Toast.makeText(this, "Scan failure", Toast.LENGTH_SHORT).show()
    }

    private var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive " + wifiManager.scanResults.size)
            unregisterReceiver(this)
            adapter.submitList(wifiManager.scanResults)
        }
    }
}
