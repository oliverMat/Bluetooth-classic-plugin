package oliver.mat.bluetooth_classic

import android.app.Activity
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import oliver.mat.bluetooth_classic.bluetooth_adapter.BluetoothAdapter
import oliver.mat.bluetooth_classic.bluetooth_socket.BluetoothSocket
import oliver.mat.bluetooth_classic.permissions.CheckSelfPermission

/** BluetoothClassicPlugin */
class BluetoothClassicPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var activity: Activity

    private val bluetoothAdapter = BluetoothAdapter()
    private val bluetoothSocket = BluetoothSocket()

    private val coroutine: GlobalScope = GlobalScope

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, BLUETOOTH_METHOD_CHANNEL)
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

        /** BluetoothAdapter */
        if (call.method == INIT_BLUETOOTH_ADAPTER) {
            bluetoothAdapter.initBluetoothAdapter(activity)
            result.success(true)
        }

        if (call.method == CHECK_PERMISSION) {
            result.success(CheckSelfPermission.checkVersion(activity))
        }

        if (call.method == IS_ENABLE_BLUETOOTH) {
            result.success(bluetoothAdapter.isEnableBluetooth())
        }

        if (call.method == IS_DISCOVERY_DEVICE) {
            result.success(bluetoothAdapter.isDiscoveryDevice())
        }

        if (call.method == ENABLE_BLUETOOTH) {
            bluetoothAdapter.enableBluetooth(activity)
            result.success(true)
        }

        if (call.method == START_DISCOVERY_DEVICE) {
            bluetoothAdapter.startDeviceDiscovery()
            result.success(true)
        }

        if (call.method == STOP_DISCOVERY_DEVICE) {
            bluetoothAdapter.stopDeviceDiscovery()
            result.success(true)
        }

        if (call.method == LIST_NEW_DEVICES) {
            result.success(bluetoothAdapter.listNewDevices())
        }

        if (call.method == LIST_PAIRED_DEVICES) {
            result.success(bluetoothAdapter.listPairedDevices())
        }

        if (call.method == CALL_PAIRED_DEVICES) {
            bluetoothAdapter.callPairedDevices()
            result.success(true)
        }

        if (call.method == REGISTER_BROADCAST_RECEIVER) {
            bluetoothAdapter.registerBroadcastReceiver(activity)
            result.success(true)
        }

        if (call.method == UNREGISTER_BROADCAST_RECEIVER) {
            bluetoothAdapter.unregisterBroadcastReceiver(activity)
            result.success(true)
        }

        /** BluetoothSocket */
        if (call.method == INIT_BLUETOOTH_SOCKET) {
            bluetoothSocket.initBluetoothSocket(activity,
                    call.argument<String>(ARGUMENT_ADDRESS).toString(),
                    call.argument<String>(ARGUMENT_UUID).toString())
            result.success(true)
        }

        if (call.method == IS_CONNECT_BLUETOOTH_SOCKET) {
            result.success(bluetoothSocket.isConnectBluetoothSocket())
        }

        if (call.method == CONNECT_BLUETOOTH_SOCKET) {
            coroutine.launch {
                bluetoothSocket.connectBluetoothSocket()
                result.success(true)
            }
        }

        if (call.method == CLOSE_BLUETOOTH_SOCKET) {
            coroutine.launch {
                bluetoothSocket.closeBluetoothSocket()
                result.success(true)
            }
        }

        if (call.method == INPUT_STREAM_BLUETOOTH_SOCKET) {
            coroutine.launch { result.success(bluetoothSocket.inputStreamBluetoothSocket()) }
        }

        if (call.method == OUTPUT_STREAM_BLUETOOTH_SOCKET) {
            coroutine.launch {
                val byte: ByteArray = call.argument<ByteArray>(ARGUMENT_OUTPUT_STREAM)!!
                bluetoothSocket.outputStreamBluetoothSocket(byte)
                result.success(true)
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        bluetoothAdapter.initBluetoothAdapter(activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
        bluetoothAdapter.unregisterBroadcastReceiver(activity)
        coroutine.cancel()
    }

    companion object {
        private const val BLUETOOTH_METHOD_CHANNEL = "bluetoothMethodChannel"

        /** BluetoothAdapter */
        private const val INIT_BLUETOOTH_ADAPTER = "initBluetoothAdapter"
        private const val CHECK_PERMISSION = "checkPermission"
        private const val IS_ENABLE_BLUETOOTH = "isEnableBluetooth"
        private const val IS_DISCOVERY_DEVICE = "isDiscoveryDevice"
        private const val ENABLE_BLUETOOTH = "enableBluetooth"
        private const val START_DISCOVERY_DEVICE = "startDeviceDiscovery"
        private const val STOP_DISCOVERY_DEVICE = "stopDeviceDiscovery"
        private const val LIST_NEW_DEVICES = "listNewDevices"
        private const val LIST_PAIRED_DEVICES = "listPairedDevices"
        private const val CALL_PAIRED_DEVICES = "callPairedDevices"
        private const val REGISTER_BROADCAST_RECEIVER = "registerBroadcastReceiver"
        private const val UNREGISTER_BROADCAST_RECEIVER = "unregisterBroadcastReceiver"

        /** BluetoothSocket */
        private const val INIT_BLUETOOTH_SOCKET = "initBluetoothSocket"
        private const val ARGUMENT_ADDRESS = "address"
        private const val ARGUMENT_UUID = "uuid"
        private const val IS_CONNECT_BLUETOOTH_SOCKET = "isConnectBluetoothSocket"
        private const val CONNECT_BLUETOOTH_SOCKET = "connectBluetoothSocket"
        private const val CLOSE_BLUETOOTH_SOCKET = "closeBluetoothSocket"
        private const val INPUT_STREAM_BLUETOOTH_SOCKET = "inputStreamBluetoothSocket"
        private const val OUTPUT_STREAM_BLUETOOTH_SOCKET = "outputStreamBluetoothSocket"
        private const val ARGUMENT_OUTPUT_STREAM = "byteOutputStream"
    }

}
