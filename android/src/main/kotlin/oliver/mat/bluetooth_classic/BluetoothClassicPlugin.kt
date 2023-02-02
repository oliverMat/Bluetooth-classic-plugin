package oliver.mat.bluetooth_classic

import android.app.Activity
import android.os.Handler
import android.os.Looper
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import oliver.mat.bluetooth_classic.bluetooth_adapter.BluetoothAdapter
import oliver.mat.bluetooth_classic.bluetooth_socket.BluetoothSocket
import oliver.mat.bluetooth_classic.event_channel.IsEnableBluetoothEvent
import oliver.mat.bluetooth_classic.event_channel.IsPermissionsGrantedEvent
import oliver.mat.bluetooth_classic.event_channel.IsSocketConnectEvent
import oliver.mat.bluetooth_classic.permissions.CheckSelfPermission
import oliver.mat.bluetooth_classic.util.Constant

/** BluetoothClassicPlugin */
class BluetoothClassicPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var methodChannel: MethodChannel
    private lateinit var isPermissionsGrantedEvent: EventChannel
    private lateinit var isEnableBluetoothEvent: EventChannel
    private lateinit var isSocketConnectEvent: EventChannel

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothSocket: BluetoothSocket? = null
    private var checkSelfPermission: CheckSelfPermission? = null

    private var handler = Handler(Looper.getMainLooper())
    private lateinit var activity: Activity

    private var isSocketConnect: IsSocketConnectEvent = IsSocketConnectEvent(handler)


    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, Constant.BLUETOOTH_METHOD_CHANNEL)
        methodChannel.setMethodCallHandler(this)

        isPermissionsGrantedEvent = EventChannel(flutterPluginBinding.binaryMessenger, Constant.IS_PERMISSIONS_GRANTED)
        isEnableBluetoothEvent = EventChannel(flutterPluginBinding.binaryMessenger, Constant.IS_ENABLE_BLUETOOTH)
        isSocketConnectEvent = EventChannel(flutterPluginBinding.binaryMessenger, Constant.IS_CONNECT_BLUETOOTH_SOCKET)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {

        /** BluetoothAdapter */
        if (call.method == Constant.INIT_BLUETOOTH_ADAPTER) {
            bluetoothAdapter!!.initBluetoothAdapter()
            result.success(true)
        }

        if (call.method == Constant.IS_DISCOVERY_DEVICE) {
            result.success(bluetoothAdapter!!.isDiscoveryDevice())
        }

        if (call.method == Constant.ENABLE_BLUETOOTH) {
            bluetoothAdapter!!.enableBluetooth()
            result.success(true)
        }

        if (call.method == Constant.START_DISCOVERY_DEVICE) {
            bluetoothAdapter!!.startDeviceDiscovery()
            result.success(true)
        }

        if (call.method == Constant.STOP_DISCOVERY_DEVICE) {
            bluetoothAdapter!!.stopDeviceDiscovery()
            result.success(true)
        }

        if (call.method == Constant.LIST_NEW_DEVICES) {
            result.success(bluetoothAdapter!!.listNewDevices())
        }

        if (call.method == Constant.LIST_PAIRED_DEVICES) {
            result.success(bluetoothAdapter!!.listPairedDevices())
        }

        if (call.method == Constant.CALL_PAIRED_DEVICES) {
            bluetoothAdapter!!.callPairedDevices()
            result.success(true)
        }

        /** BluetoothSocket */
        if (call.method == Constant.INIT_BLUETOOTH_SOCKET) {
            bluetoothSocket!!.initBluetoothSocket(
                call.argument<String>(Constant.ARGUMENT_ADDRESS).toString(),
                call.argument<String>(Constant.ARGUMENT_UUID).toString())
            result.success(true)
        }

        if (call.method == Constant.CONNECT_BLUETOOTH_SOCKET) {
            bluetoothSocket!!.connectBluetoothSocket()
            result.success(true)
        }

        if (call.method == Constant.CLOSE_BLUETOOTH_SOCKET) {
            bluetoothSocket!!.closeBluetoothSocket()
            result.success(true)
        }

        if (call.method == Constant.INPUT_STREAM_BLUETOOTH_SOCKET) {
            result.success(bluetoothSocket!!.inputStreamBluetoothSocket())
        }

        if (call.method == Constant.OUTPUT_STREAM_BLUETOOTH_SOCKET) {
            val byte: ByteArray = call.argument<ByteArray>(Constant.ARGUMENT_OUTPUT_STREAM)!!
            bluetoothSocket!!.outputStreamBluetoothSocket(byte)
            result.success(true)
        }

        /** CheckSelfPermission */
        if (call.method == Constant.REQUIRE_PERMISSIONS) {
            checkSelfPermission!!.requirePermissions()
            result.success(true)
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel.setMethodCallHandler(null)

        isPermissionsGrantedEvent.setStreamHandler(null)
        isEnableBluetoothEvent.setStreamHandler(null)
        isSocketConnectEvent.setStreamHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity

        isSocketConnect.initBcrConnectEvent(isSocketConnect, binding.activity)

        bluetoothAdapter = BluetoothAdapter(binding.activity)
        bluetoothSocket = BluetoothSocket(binding.activity)
        checkSelfPermission = CheckSelfPermission(binding.activity)

        bluetoothAdapter!!.registerBroadcastReceiver()

        isPermissionsGrantedEvent.setStreamHandler(IsPermissionsGrantedEvent(checkSelfPermission, handler))
        isEnableBluetoothEvent.setStreamHandler(IsEnableBluetoothEvent(bluetoothAdapter, handler))
        isSocketConnectEvent.setStreamHandler(isSocketConnect)

    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivity() {
        activity.unregisterReceiver(isSocketConnect)
        bluetoothAdapter!!.unregisterBroadcastReceiver()
        handler.removeCallbacksAndMessages(null)
    }
}