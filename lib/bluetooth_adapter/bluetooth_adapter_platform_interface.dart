import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'package:bluetooth_classic/model/Device.dart';
import 'bluetooth_adapter_method_channel.dart';


abstract class BluetoothAdapterPlatform extends PlatformInterface {
  /// Constructs a BluetoothClassicPlatform.
  BluetoothAdapterPlatform() : super(token: _token);

  static final Object _token = Object();

  static BluetoothAdapterPlatform _instance = MethodChannelBluetoothAdapter();

  /// The default instance of [BluetoothAdapterPlatform] to use.
  ///
  /// Defaults to [MethodChannelBluetoothAdapter].
  static BluetoothAdapterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [BluetoothAdapterPlatform] when
  /// they register themselves.
  static set instance(BluetoothAdapterPlatform instance) {
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
}
