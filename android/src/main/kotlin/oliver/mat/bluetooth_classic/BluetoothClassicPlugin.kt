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

/** BluetoothClassicPlugin */
class BluetoothClassicPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var activity: Activity

    private val bluetoothConnection = BluetoothConnection()

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "bluetooth_classic")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "initBluetoothAdapter") {
            bluetoothConnection.initBluetoothAdapter(activity)
            result.success(true)
        }

        if (call.method == "checkPermission") {
            result.success(CheckSelfPermission.checkVersion(activity))
        }

        if (call.method == "enableBluetooth") {
            result.success(bluetoothConnection.enableBluetooth(activity))
        }

        if (call.method == "startDeviceDiscovery") {
            bluetoothConnection.startDeviceDiscovery()
            result.success(true)
        }

        if (call.method == "stopDeviceDiscovery") {
            bluetoothConnection.stopDeviceDiscovery()
            result.success(true)
        }

        if (call.method == "listDevices") {
            result.success(bluetoothConnection.listDevices())
        }

        if (call.method == "registerBroadcastReceiver") {
            bluetoothConnection.registerBroadcastReceiver(activity)
            result.success(true)
        }

        if (call.method == "unregisterBroadcastReceiver") {
            bluetoothConnection.unregisterBroadcastReceiver(activity)
            result.success(true)
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        bluetoothConnection.initBluetoothAdapter(activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
        bluetoothConnection.unregisterBroadcastReceiver(activity)
    }

}
