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
import oliver.mat.bluetooth_classic.bluetooth_adapter.BluetoothAdapter
import oliver.mat.bluetooth_classic.permissions.CheckSelfPermission

/** BluetoothClassicPlugin */
class MethodChannelBluetoothAdapter : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var activity: Activity

    private val bluetoothAdapter = BluetoothAdapter()

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, BLUETOOTH_ADAPTER)
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

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
    }

    companion object {
        private const val BLUETOOTH_ADAPTER = "bluetooth_adapter"
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
    }

}
