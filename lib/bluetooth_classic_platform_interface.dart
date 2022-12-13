import 'dart:ffi';
import 'dart:typed_data';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'package:bluetooth_classic/model/Device.dart';
import 'bluetooth_classic_method_channel.dart';

abstract class BluetoothClassicPlatform extends PlatformInterface {
  /// Constructs a BluetoothClassicPlatform.
  BluetoothClassicPlatform() : super(token: _token);

  static final Object _token = Object();

  static BluetoothClassicPlatform _instance = MethodChannelBluetoothClassic();

  /// The default instance of [BluetoothClassicPlatform] to use.
  ///
  /// Defaults to [MethodChannelBluetoothClassic].
  static BluetoothClassicPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [BluetoothClassicPlatform] when
  /// they register themselves.
  static set instance(BluetoothClassicPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  /// BluetoothAdapter */
  void initBluetoothAdapter() {
    throw UnimplementedError('initBluetoothAdapter() has not been implemented.');
  }

  Future<bool> checkPermission() {
    throw UnimplementedError('checkPermission() has not been implemented.');
  }

  Future<bool> isEnableBluetooth() {
    throw UnimplementedError('isEnableBluetooth() has not been implemented.');
  }

  Future<bool> isDiscoveryDevice() {
    throw UnimplementedError('isDiscoveryDevice() has not been implemented.');
  }

  void enableBluetooth() {
    throw UnimplementedError('enableBluetooth() has not been implemented.');
  }

  void startDeviceDiscovery() {
    throw UnimplementedError('startDeviceDiscovery() has not been implemented.');
  }

  void stopDeviceDiscovery() {
    throw UnimplementedError('stopDeviceDiscovery() has not been implemented.');
  }

  Future<List<Device>> listNewDevices() {
    throw UnimplementedError('listNewDevices() has not been implemented.');
  }

  Future<List<Device>> listPairedDevices() {
    throw UnimplementedError('listPairedDevices() has not been implemented.');
  }

  void callPairedDevices() {
    throw UnimplementedError('callPairedDevices() has not been implemented.');
  }

  void registerBroadcastReceiver() {
    throw UnimplementedError('registerBroadcastReceiver() has not been implemented.');
  }

  void unregisterBroadcastReceiver() {
    throw UnimplementedError('unregisterBroadcastReceiver() has not been implemented.');
  }

  /// BluetoothSocket */
  Future<void> initBluetoothSocket(String address, String uuid) {
    throw UnimplementedError('initBluetoothSocket() has not been implemented.');
  }

  Future<bool> isConnectBluetoothSocket() {
    throw UnimplementedError('isConnectBluetoothSocket() has not been implemented.');
  }

  void connectBluetoothSocket() {
    throw UnimplementedError('connectBluetoothSocket() has not been implemented.');
  }

  void closeBluetoothSocket() {
    throw UnimplementedError('closeBluetoothSocket() has not been implemented.');
  }

  Future<Uint8List> inputStreamBluetoothSocket() {
    throw UnimplementedError('inputStreamBluetoothSocket() has not been implemented.');
  }

  Future<void> outputStreamBluetoothSocket(Uint8List byte) {
    throw UnimplementedError('outputStreamBluetoothSocket() has not been implemented.');
  }
}
