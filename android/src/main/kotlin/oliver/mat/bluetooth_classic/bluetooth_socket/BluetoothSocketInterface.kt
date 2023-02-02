package oliver.mat.bluetooth_classic.bluetooth_socket

interface BluetoothSocketInterface {

    fun initBluetoothSocket(address: String, uuid: String)

    fun connectBluetoothSocket()

    fun closeBluetoothSocket()

    fun inputStreamBluetoothSocket(): ByteArray

    fun outputStreamBluetoothSocket(bytes: ByteArray)
}