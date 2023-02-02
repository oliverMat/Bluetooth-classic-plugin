package oliver.mat.bluetooth_classic.event_channel

import android.os.Handler
import io.flutter.plugin.common.EventChannel
import oliver.mat.bluetooth_classic.bluetooth_adapter.BluetoothAdapter

class IsEnableBluetoothEvent (private var bluetoothAdapter: BluetoothAdapter?, private var handler: Handler) : EventChannel.StreamHandler {

    private var eventSink: EventChannel.EventSink? = null

    override fun onListen(p0: Any?, event: EventChannel.EventSink) {
        eventSink = event
        val runnable: Runnable = object : Runnable {
            override fun run() {
                handler.post {
                    event.success(bluetoothAdapter!!.isEnableBluetooth())
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    override fun onCancel(p0: Any?) {
        eventSink = null
    }

}