package oliver.mat.bluetooth_classic.bluetooth_socket

import android.app.Activity
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothSocket {

    private var bluetoothSocket: BluetoothSocket? = null

    fun initBluetoothSocket(activity: Activity, address: String, uuid: String) {
        val bluetoothAdapter = (activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        bluetoothSocket = bluetoothAdapter.getRemoteDevice(address).createRfcommSocketToServiceRecord(UUID.fromString(uuid))
    }

    fun isConnectBluetoothSocket(): Boolean {
        return bluetoothSocket!!.isConnected
    }

    fun connectBluetoothSocket() {
        if (bluetoothSocket != null) {
            bluetoothSocket!!.connect()
        }
    }

    fun closeBluetoothSocket() {
        if (bluetoothSocket != null) {
            bluetoothSocket!!.close()
        }
    }

    fun inputStreamBluetoothSocket(): InputStream {
        return bluetoothSocket!!.inputStream
    }

    fun outputStreamBluetoothSocket(): OutputStream {
        return bluetoothSocket!!.outputStream
    }

}