import 'bluetooth_adapter_platform_interface.dart';
import 'package:bluetooth_classic/model/Device.dart';

class BluetoothAdapter {
  void initBluetoothAdapter() {
    return BluetoothAdapterPlatform.instance.initBluetoothAdapter();
  }

  Future<bool> checkPermission() {
    return BluetoothAdapterPlatform.instance.checkPermission();
  }

  Future<bool> isEnableBluetooth() {
    return BluetoothAdapterPlatform.instance.isEnableBluetooth();
  }

  Future<bool> isDiscoveryDevice() {
    return BluetoothAdapterPlatform.instance.isDiscoveryDevice();
  }

  void enableBluetooth() {
    return BluetoothAdapterPlatform.instance.enableBluetooth();
  }

  void startDeviceDiscovery() {
    return BluetoothAdapterPlatform.instance.startDeviceDiscovery();
  }

  void stopDeviceDiscovery() {
    return BluetoothAdapterPlatform.instance.stopDeviceDiscovery();
  }

  Future<List<Device>> listNewDevices() {
    return BluetoothAdapterPlatform.instance.listNewDevices();
  }

  Future<List<Device>> listPairedDevices() {
    return BluetoothAdapterPlatform.instance.listPairedDevices();
  }

  void callPairedDevices() {
    return BluetoothAdapterPlatform.instance.callPairedDevices();
  }

  void registerBroadcastReceiver() {
    return BluetoothAdapterPlatform.instance.registerBroadcastReceiver();
  }

  void unregisterBroadcastReceiver() {
    return BluetoothAdapterPlatform.instance.unregisterBroadcastReceiver();
  }
}
