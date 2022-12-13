package oliver.mat.bluetooth_classic.bluetooth_adapter

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.gson.Gson
import oliver.mat.bluetooth_classic.model.Device

class BluetoothAdapter: BluetoothAdapterInterface {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var listNewDevices: MutableList<String> = mutableListOf()
    private var listPairedDevices: MutableList<String> = mutableListOf()

    override fun initBluetoothAdapter(activity: Activity) {
        if (bluetoothAdapter == null) {
            bluetoothAdapter = (activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        }
    }

    override fun isEnableBluetooth(): Boolean {
        return bluetoothAdapter!!.isEnabled
    }

    override fun isDiscoveryDevice(): Boolean {
        return bluetoothAdapter!!.isDiscovering
    }

    override fun enableBluetooth(activity: Activity) {
        if (bluetoothAdapter?.isEnabled == false) {
            startActivityForResult(activity, Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BLUETOOTH, null)
        }
    }

    override fun startDeviceDiscovery() {
        clearList()
        bluetoothAdapter!!.startDiscovery()
    }

    override fun stopDeviceDiscovery() {
        bluetoothAdapter!!.cancelDiscovery()
    }

    override fun registerBroadcastReceiver(activity: Activity) {
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        }

        activity.registerReceiver(receiver, filter)
    }

    override fun unregisterBroadcastReceiver(activity: Activity) {
        activity.unregisterReceiver(receiver)
    }

    override fun listNewDevices(): List<String> {
        return listNewDevices
    }

    override fun listPairedDevices(): List<String> {
        return listPairedDevices
    }

    override fun callPairedDevices() {
        bluetoothAdapter?.bondedDevices?.forEach { device ->

            val deviceObject = Gson().toJson(Device(
                    name = device.name,
                    deviceHardwareAddress = device.address,
                    paired = true))

            if (!listPairedDevices.contains(deviceObject)) {
                listPairedDevices.add(deviceObject)
            }
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action.toString()) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!

                    val deviceObject = Gson().toJson(Device(
                            name = device.name,
                            deviceHardwareAddress = device.address,
                            paired = false))

                    if (!listNewDevices.contains(deviceObject)) {
                        listNewDevices.add(deviceObject)
                    }
                }
            }
        }
    }

    private fun clearList() {
        listNewDevices.clear()
        listPairedDevices.clear()

    }

    companion object {
        private const val REQUEST_ENABLE_BLUETOOTH = 1
    }
}