package oliver.mat.bluetooth_classic

import android.app.Activity
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import oliver.mat.bluetooth_classic.bluetooth_socket.BluetoothSocket

class MethodChannelBluetoothSocket : FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var activity: Activity

    private val bluetoothSocket = BluetoothSocket()

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, BLUETOOTH_SOCKET)
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {

        channel.invokeMethod(INIT_BLUETOOTH_SOCKET, false, object : MethodChannel.Result {
            override fun success(@Nullable result: Any?) {
                Log.i("fromInvoke", "success" + result.toString())
            }

            override fun error(errorCode: String, @Nullable errorMessage: String?, @Nullable errorDetails: Any?) {
                Log.i("fromInvoke", "failed$errorMessage")
            }

            override fun notImplemented() {
                Log.i("fromInvoke", "not implemented")
            }
        })

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

    }

    companion object {
        private const val BLUETOOTH_SOCKET = "bluetooth_socket"
        private const val INIT_BLUETOOTH_SOCKET = "initBluetoothSocket"
    }
}