package oliver.mat.bluetooth_classic.bluetooth_adapter

import android.app.Activity

interface BluetoothAdapterInterface {

    fun initBluetoothAdapter(activity: Activity)

    fun isEnableBluetooth(): Boolean

    fun isDiscoveryDevice(): Boolean

    fun enableBluetooth(activity: Activity)

    fun startDeviceDiscovery()

    fun stopDeviceDiscovery()

    fun registerBroadcastReceiver(activity: Activity)

    fun unregisterBroadcastReceiver(activity: Activity)

    fun listNewDevices(): List<String>

    fun listPairedDevices(): List<String>

    fun callPairedDevices()
}