package oliver.mat.bluetooth_classic.util

object Constant {

    const val BLUETOOTH_METHOD_CHANNEL = "bluetoothMethodChannel"

    /** BluetoothAdapter */
    const val INIT_BLUETOOTH_ADAPTER = "initBluetoothAdapter"
    const val IS_PERMISSIONS_GRANTED = "isPermissionsGranted"
    const val IS_ENABLE_BLUETOOTH = "isEnableBluetooth"
    const val IS_DISCOVERY_DEVICE = "isDiscoveryDevice"
    const val ENABLE_BLUETOOTH = "enableBluetooth"
    const val START_DISCOVERY_DEVICE = "startDeviceDiscovery"
    const val STOP_DISCOVERY_DEVICE = "stopDeviceDiscovery"
    const val LIST_NEW_DEVICES = "listNewDevices"
    const val LIST_PAIRED_DEVICES = "listPairedDevices"
    const val CALL_PAIRED_DEVICES = "callPairedDevices"

    /** BluetoothSocket */
    const val INIT_BLUETOOTH_SOCKET = "initBluetoothSocket"
    const val ARGUMENT_ADDRESS = "address"
    const val ARGUMENT_UUID = "uuid"
    const val IS_CONNECT_BLUETOOTH_SOCKET = "isConnectBluetoothSocket"
    const val CONNECT_BLUETOOTH_SOCKET = "connectBluetoothSocket"
    const val CLOSE_BLUETOOTH_SOCKET = "closeBluetoothSocket"
    const val INPUT_STREAM_BLUETOOTH_SOCKET = "inputStreamBluetoothSocket"
    const val OUTPUT_STREAM_BLUETOOTH_SOCKET = "outputStreamBluetoothSocket"
    const val ARGUMENT_OUTPUT_STREAM = "byteOutputStream"

    /** CheckSelfPermission */
    const val REQUIRE_PERMISSIONS = "requirePermission"
}