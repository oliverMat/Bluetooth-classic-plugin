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
  Future<bool> isEnableBluetooth() async {
    final version = await methodChannel.invokeMethod<bool>('isEnableBluetooth');
    return version!;
  }

  @override
  Future<bool> isDiscoveryDevice() async {
    final version = await methodChannel.invokeMethod<bool>('isDiscoveryDevice');
    return version!;
  }

  @override
  void enableBluetooth() async {
    await methodChannel.invokeMethod<bool>('enableBluetooth');
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
  Future<List<Device>> listNewDevices() async {
    final device = await methodChannel.invokeMethod<List<dynamic>>('listNewDevices');
    return jsonDecode(device.toString())
        .map<Device>((json) => Device.fromJson(json))
        .toList();
  }

  @override
  Future<List<Device>> listPairedDevices() async {
    final device = await methodChannel.invokeMethod<List<dynamic>>('listPairedDevices');
    return jsonDecode(device.toString())
        .map<Device>((json) => Device.fromJson(json))
        .toList();
  }

  @override
  void callPairedDevices() async {
    await methodChannel.invokeMethod('callPairedDevices');
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
