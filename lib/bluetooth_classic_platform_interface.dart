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

  void initBluetoothAdapter() {
    throw "Deu error";
  }

  Future<bool> checkPermission() {
    throw "Deu error";
  }

  Future<bool> isEnableBluetooth() {
    throw "Deu error";
  }

  Future<bool> isDiscoveryDevice() {
    throw "Deu error";
  }

  void enableBluetooth() {
    throw "Deu error";
  }

  void startDeviceDiscovery() {
    throw "Deu error";
  }

  void stopDeviceDiscovery() {
    throw "Deu error";
  }

  Future<List<Device>> listNewDevices() {
    throw "Deu error";
  }

  Future<List<Device>> listPairedDevices() {
    throw "Deu error";
  }

  void callPairedDevices() {
    throw "Deu error";
  }

  void registerBroadcastReceiver() {
    throw "Deu error";
  }

  void unregisterBroadcastReceiver() {
    throw "Deu error";
  }

  Future<void> initBluetoothSocket(String address, String uuid) {
    throw "Deu error";
  }
}
