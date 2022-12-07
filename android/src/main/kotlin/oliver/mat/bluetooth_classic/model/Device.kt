package oliver.mat.bluetooth_classic.model

class Device(
        var name: String = "",
        var deviceHardwareAddress: String = "",
        var paired: Boolean = false
) {}