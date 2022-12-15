import 'dart:typed_data';

import 'bluetooth_classic_platform_interface.dart';
import 'package:bluetooth_classic/model/Device.dart';

class BluetoothClassic {
  /// BluetoothAdapter */
  void initBluetoothAdapter() {
    return BluetoothClassicPlatform.instance.initBluetoothAdapter();
  }

  Future<bool> checkPermission() {
    return BluetoothClassicPlatform.instance.checkPermission();
  }

  Future<bool> isEnableBluetooth() {
    return BluetoothClassicPlatform.instance.isEnableBluetooth();
  }

  Future<bool> isDiscoveryDevice() {
    return BluetoothClassicPlatform.instance.isDiscoveryDevice();
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

  /// BluetoothSocket */
  Future<void> initBluetoothSocket(String address, String uuid) {
    return BluetoothClassicPlatform.instance.initBluetoothSocket(address, uuid);
  }

  Future<bool> isConnectBluetoothSocket() {
    return BluetoothClassicPlatform.instance.isConnectBluetoothSocket();
  }

  void connectBluetoothSocket() {
    return BluetoothClassicPlatform.instance.connectBluetoothSocket();
  }

  void closeBluetoothSocket() {
    return BluetoothClassicPlatform.instance.closeBluetoothSocket();
  }

  Future<Uint8List> inputStreamBluetoothSocket() {
    return BluetoothClassicPlatform.instance.inputStreamBluetoothSocket();
  }

  Future<void> outputStreamBluetoothSocket(Uint8List byte) {
    return BluetoothClassicPlatform.instance.outputStreamBluetoothSocket(byte);
  }
}
