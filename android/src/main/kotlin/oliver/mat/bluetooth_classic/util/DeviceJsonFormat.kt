package oliver.mat.bluetooth_classic.util

object DeviceJsonFormat {

    fun format(address: String, name: String, paired: Boolean): String {
        return "{\"deviceHardwareAddress\":\"${address}\",\"name\":\"${name}\",\"paired\":${paired}}"
    }
}