package oliver.mat.bluetooth_classic.event_channel

import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import io.flutter.plugin.common.EventChannel

class IsSocketConnectEvent(private var handler: Handler) : EventChannel.StreamHandler, BroadcastReceiver(){

    private var eventSink: EventChannel.EventSink? = null
    private var isConnected = false

    override fun onListen(p0: Any?, event: EventChannel.EventSink) {
        eventSink = event

        val runnable: Runnable = object : Runnable {
            override fun run() {
                handler.post {
                    event.success(isConnected)
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    override fun onCancel(p0: Any?) {
        eventSink = null
    }

    override fun onReceive(contxt: Context?, intent: Intent) {
        when (intent.action) {
            BluetoothDevice.ACTION_ACL_CONNECTED -> {
                isConnected = true
            }

            BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                isConnected = false
            }
        }
    }

    fun initBcrConnectEvent(receiver: BroadcastReceiver, activity: Activity) {
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
            addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        }
        activity.registerReceiver(receiver, filter)
    }
}