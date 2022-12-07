import 'bluetooth_classic_platform_interface.dart';
import 'package:bluetooth_classic/model/Device.dart';

class BluetoothClassic {
  void initBluetoothAdapter() {
    return BluetoothClassicPlatform.instance.initBluetoothAdapter();
  }

  Future<bool> checkPermission() {
    return BluetoothClassicPlatform.instance.checkPermission();
  }

  Future<bool> isEnableBluetooth() {
    return BluetoothClassicPlatform.instance.isEnableBluetooth();
  }

  void enableBluetooth() {
    return BluetoothClassicPlatform.instance.enableBluetooth();
  }

  void startDeviceDiscovery() {
    return BluetoothClassicPlatform.instance.startDeviceDiscovery();
  }

  void stopDeviceDiscovery() {
    return BluetoothClassicPlatform.instance.stopDeviceDiscovery();
  }

  Future<List<Device>> listNewDevices() {
    return BluetoothClassicPlatform.instance.listNewDevices();
  }

  Future<List<Device>> listPairedDevices() {
    return BluetoothClassicPlatform.instance.listPairedDevices();
  }

  void callPairedDevices() {
    return BluetoothClassicPlatform.instance.callPairedDevices();
  }

  void registerBroadcastReceiver() {
    return BluetoothClassicPlatform.instance.registerBroadcastReceiver();
  }

  void unregisterBroadcastReceiver() {
    return BluetoothClassicPlatform.instance.unregisterBroadcastReceiver();
  }
}
