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
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "bluetooth_adapter")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "initBluetoothAdapter") {
            bluetoothAdapter.initBluetoothAdapter(activity)
            result.success(true)
        }

        if (call.method == "checkPermission") {
            result.success(CheckSelfPermission.checkVersion(activity))
        }

        if (call.method == "isEnableBluetooth") {
            result.success(bluetoothAdapter.isEnableBluetooth())
        }

        if (call.method == "isDiscoveryDevice") {
            result.success(bluetoothAdapter.isDiscoveryDevice())
        }

        if (call.method == "enableBluetooth") {
            bluetoothAdapter.enableBluetooth(activity)
            result.success(true)
        }

        if (call.method == "startDeviceDiscovery") {
            bluetoothAdapter.startDeviceDiscovery()
            result.success(true)
        }

        if (call.method == "stopDeviceDiscovery") {
            bluetoothAdapter.stopDeviceDiscovery()
            result.success(true)
        }

        if (call.method == "listNewDevices") {
            result.success(bluetoothAdapter.listNewDevices())
        }

        if (call.method == "listPairedDevices") {
            result.success(bluetoothAdapter.listPairedDevices())
        }

        if (call.method == "callPairedDevices") {
            bluetoothAdapter.callPairedDevices()
            result.success(true)
        }

        if (call.method == "registerBroadcastReceiver") {
            bluetoothAdapter.registerBroadcastReceiver(activity)
            result.success(true)
        }

        if (call.method == "unregisterBroadcastReceiver") {
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

}
