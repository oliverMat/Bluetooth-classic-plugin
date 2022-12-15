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
import oliver.mat.bluetooth_classic.util.CheckSelfPermission
import oliver.mat.bluetooth_classic.util.Constant

/** BluetoothClassicPlugin */
class BluetoothClassicPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var activity: Activity

    private val bluetoothAdapter = BluetoothAdapter()
    private val bluetoothSocket = BluetoothSocket()

    private val coroutine: GlobalScope = GlobalScope

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, Constant.BLUETOOTH_METHOD_CHANNEL)
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

        /** BluetoothAdapter */
        if (call.method == Constant.INIT_BLUETOOTH_ADAPTER) {
            bluetoothAdapter.initBluetoothAdapter(activity)
            result.success(true)
        }

        if (call.method == Constant.CHECK_PERMISSION) {
            result.success(CheckSelfPermission.checkVersion(activity))
        }

        if (call.method == Constant.IS_ENABLE_BLUETOOTH) {
            result.success(bluetoothAdapter.isEnableBluetooth())
        }

        if (call.method == Constant.IS_DISCOVERY_DEVICE) {
            result.success(bluetoothAdapter.isDiscoveryDevice())
        }

        if (call.method == Constant.ENABLE_BLUETOOTH) {
            bluetoothAdapter.enableBluetooth(activity)
            result.success(true)
        }

        if (call.method == Constant.START_DISCOVERY_DEVICE) {
            bluetoothAdapter.startDeviceDiscovery()
            result.success(true)
        }

        if (call.method == Constant.STOP_DISCOVERY_DEVICE) {
            bluetoothAdapter.stopDeviceDiscovery()
            result.success(true)
        }

        if (call.method == Constant.LIST_NEW_DEVICES) {
            result.success(bluetoothAdapter.listNewDevices())
        }

        if (call.method == Constant.LIST_PAIRED_DEVICES) {
            result.success(bluetoothAdapter.listPairedDevices())
        }

        if (call.method == Constant.CALL_PAIRED_DEVICES) {
            bluetoothAdapter.callPairedDevices()
            result.success(true)
        }

        if (call.method == Constant.REGISTER_BROADCAST_RECEIVER) {
            bluetoothAdapter.registerBroadcastReceiver(activity)
            result.success(true)
        }

        if (call.method == Constant.UNREGISTER_BROADCAST_RECEIVER) {
            bluetoothAdapter.unregisterBroadcastReceiver(activity)
            result.success(true)
        }

        /** BluetoothSocket */
        if (call.method == Constant.INIT_BLUETOOTH_SOCKET) {
            bluetoothSocket.initBluetoothSocket(activity,
                    call.argument<String>(Constant.ARGUMENT_ADDRESS).toString(),
                    call.argument<String>(Constant.ARGUMENT_UUID).toString())
            result.success(true)
        }

        if (call.method == Constant.IS_CONNECT_BLUETOOTH_SOCKET) {
            result.success(bluetoothSocket.isConnectBluetoothSocket())
        }

        if (call.method == Constant.CONNECT_BLUETOOTH_SOCKET) {
            coroutine.launch {
                bluetoothSocket.connectBluetoothSocket()
                result.success(true)
            }
        }

        if (call.method == Constant.CLOSE_BLUETOOTH_SOCKET) {
            coroutine.launch {
                bluetoothSocket.closeBluetoothSocket()
                result.success(true)
            }
        }

        if (call.method == Constant.INPUT_STREAM_BLUETOOTH_SOCKET) {
            coroutine.launch { result.success(bluetoothSocket.inputStreamBluetoothSocket()) }
        }

        if (call.method == Constant.OUTPUT_STREAM_BLUETOOTH_SOCKET) {
            coroutine.launch {
                val byte: ByteArray = call.argument<ByteArray>(Constant.ARGUMENT_OUTPUT_STREAM)!!
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

}
