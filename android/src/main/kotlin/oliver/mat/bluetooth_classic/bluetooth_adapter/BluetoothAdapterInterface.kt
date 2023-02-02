package oliver.mat.bluetooth_classic.bluetooth_adapter

import android.app.Activity

interface BluetoothAdapterInterface {

    fun initBluetoothAdapter()

    fun isEnableBluetooth(): Boolean

    fun isDiscoveryDevice(): Boolean

    fun enableBluetooth()

    fun startDeviceDiscovery()

    fun stopDeviceDiscovery()

    fun listNewDevices(): List<String>

    fun listPairedDevices(): List<String>

    fun callPairedDevices()

    fun registerBroadcastReceiver()

    fun unregisterBroadcastReceiver()
}