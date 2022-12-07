import 'dart:convert';

import 'package:bluetooth_classic/model/Device.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'bluetooth_classic_platform_interface.dart';



/// An implementation of [BluetoothClassicPlatform] that uses method channels.
class MethodChannelBluetoothClassic extends BluetoothClassicPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('bluetooth_classic');

  @override
  void initBluetoothAdapter() async {
    await methodChannel.invokeMethod('initBluetoothAdapter');
  }

  @override
  Future<bool> checkPermission() async {
    final version = await methodChannel.invokeMethod<bool>('checkPermission');
    return version!;
  }

  @override
  Future<bool> enableBluetooth() async {
    final version = await methodChannel.invokeMethod<bool>('enableBluetooth');
    return version!;
  }

  @override
  void startDeviceDiscovery() async {
    await methodChannel.invokeMethod('startDeviceDiscovery');
  }

  @override
  void stopDeviceDiscovery() async {
    await methodChannel.invokeMethod('stopDeviceDiscovery');
  }

  @override
  Future<List<Device>> listDevices() async {
    final version = await methodChannel.invokeMethod<Map<String, dynamic>>('listDevices');
    var name = version!['NAME'] as String;
    var address = version['ADDRESS'] as String;
    var paired = version['PAIRED'] as bool;

    List<Device> device = Device(name, address, paired) as List<Device>;

    return device;
  }

  @override
  void registerBroadcastReceiver() async {
    await methodChannel.invokeMethod('registerBroadcastReceiver');
  }

  @override
  void unregisterBroadcastReceiver() async {
    await methodChannel.invokeMethod('unregisterBroadcastReceiver');
  }
}
