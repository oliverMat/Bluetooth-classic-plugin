package oliver.mat.bluetooth_classic

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import oliver.mat.bluetooth_classic.model.Device

class BluetoothConnection {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var devices: MutableList<Device> = mutableListOf()

    fun initBluetoothAdapter(activity: Activity) {
        if (bluetoothAdapter == null) {
            bluetoothAdapter = (activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        }
    }

    fun enableBluetooth(activity: Activity): Boolean {
        var enable = false

        if (bluetoothAdapter?.isEnabled == false) {
            startActivityForResult(activity, Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BLUETOOTH, null)
            enable = true
        }

        return enable
    }

    fun startDeviceDiscovery() {
        bluetoothAdapter!!.startDiscovery()
    }

    fun stopDeviceDiscovery() {
        bluetoothAdapter!!.cancelDiscovery()
    }

    fun registerBroadcastReceiver(activity: Activity) {
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        }

        listPairedDevices()

        activity.registerReceiver(receiver, filter)
    }

    fun unregisterBroadcastReceiver(activity: Activity) {
        activity.unregisterReceiver(receiver)
    }

    fun listDevices(): MutableList<Device> {
        return devices
    }

    fun listPairedDevices() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address

            val deviceObject = Device(
                    name = deviceName,
                    deviceHardwareAddress = deviceHardwareAddress,
                    paired = true)

            if (!devices.contains(deviceObject)) {
                devices.add(deviceObject)
            }
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action.toString()) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address

                    val deviceObject = Device(
                            name = deviceName,
                            deviceHardwareAddress = deviceHardwareAddress,
                            paired = false)

                    if (!devices.contains(deviceObject)) {
                        devices.add(deviceObject)
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_ENABLE_BLUETOOTH = 2
    }
}