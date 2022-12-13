package oliver.mat.bluetooth_classic.bluetooth_socket

import android.app.Activity
import java.io.InputStream
import java.io.OutputStream

interface BluetoothSocketInterface {

    fun initBluetoothSocket(activity: Activity, address: String, uuid: String)

    fun isConnectBluetoothSocket(): Boolean

    fun connectBluetoothSocket()

    fun closeBluetoothSocket()

    fun inputStreamBluetoothSocket(): ByteArray

    fun outputStreamBluetoothSocket(bytes: ByteArray)
}