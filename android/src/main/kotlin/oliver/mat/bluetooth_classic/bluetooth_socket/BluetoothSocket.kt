package oliver.mat.bluetooth_classic.bluetooth_socket

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class BluetoothSocket(private var activity: Activity): BluetoothSocketInterface {

    private var bluetoothSocket: BluetoothSocket? = null
    private val coroutine: GlobalScope = GlobalScope

    override fun initBluetoothSocket(address: String, uuid: String) {
        bluetoothSocket = initBluetoothAdapter(activity).getRemoteDevice(address).createRfcommSocketToServiceRecord(UUID.fromString(uuid))
    }

    override fun connectBluetoothSocket() {
        coroutine.launch {
            try {
                if (bluetoothSocket != null) {
                    bluetoothSocket!!.connect()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun closeBluetoothSocket() {
        coroutine.launch {
            try {
                if (bluetoothSocket != null) {
                    bluetoothSocket!!.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun inputStreamBluetoothSocket(): ByteArray {
        val bytes = ByteArray(1024)
        try {
            bluetoothSocket!!.inputStream.read(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bytes
    }

    override fun outputStreamBluetoothSocket(bytes: ByteArray) {
        try {
            bluetoothSocket!!.outputStream.write(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun initBluetoothAdapter(activity: Activity): BluetoothAdapter {
        return (activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }
}