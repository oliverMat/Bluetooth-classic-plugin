package oliver.mat.bluetooth_classic.bluetooth_socket

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothSocket {

    private var bluetoothSocket: BluetoothSocket? = null

    private fun initBluetoothAdapter(activity: Activity): BluetoothAdapter {
        return (activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }

    fun initBluetoothSocket(activity: Activity, address: String, uuid: String) {
        bluetoothSocket = initBluetoothAdapter(activity).getRemoteDevice(address).createRfcommSocketToServiceRecord(UUID.fromString(uuid))
    }

    fun isConnectBluetoothSocket(): Boolean {
        return bluetoothSocket!!.isConnected
    }

    fun connectBluetoothSocket() {
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket!!.connect()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun closeBluetoothSocket() {
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket!!.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun inputStreamBluetoothSocket(): InputStream {
        return bluetoothSocket!!.inputStream
    }

    fun outputStreamBluetoothSocket(): OutputStream {
        return bluetoothSocket!!.outputStream
    }

}