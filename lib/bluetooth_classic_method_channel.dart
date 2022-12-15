import 'dart:convert';

import 'package:bluetooth_classic/util/constants.dart';
import 'package:bluetooth_classic/model/Device.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'bluetooth_classic_platform_interface.dart';

/// An implementation of [BluetoothClassicPlatform] that uses method channels.
class MethodChannelBluetoothClassic extends BluetoothClassicPlatform {
  @visibleForTesting
  final methodChannel = const MethodChannel(Constants.bluetoothMethodChannel);

  /// BluetoothAdapter */
  @override
  void initBluetoothAdapter() async {
    await methodChannel.invokeMethod(Constants.initBluetoothAdapter);
  }

  @override
  Future<bool> checkPermission() async {
    return await methodChannel.invokeMethod(Constants.checkPermission);
  }

  @override
  Future<bool> isEnableBluetooth() async {
    return await methodChannel.invokeMethod(Constants.isEnableBluetooth);
  }

  @override
  Future<bool> isDiscoveryDevice() async {
    return await methodChannel.invokeMethod(Constants.isDiscoveryDevice);
  }

  @override
  void enableBluetooth() async {
    await methodChannel.invokeMethod(Constants.enableBluetooth);
  }

  @override
  void startDeviceDiscovery() async {
    await methodChannel.invokeMethod(Constants.startDeviceDiscovery);
  }

  @override
  void stopDeviceDiscovery() async {
    await methodChannel.invokeMethod(Constants.stopDeviceDiscovery);
  }

  @override
  Future<List<Device>> listNewDevices() async {
    final device = await methodChannel
        .invokeMethod<List<dynamic>>(Constants.listNewDevices);
    return jsonDecode(device.toString())
        .map<Device>((json) => Device.fromJson(json))
        .toList();
  }

  @override
  Future<List<Device>> listPairedDevices() async {
    final device = await methodChannel
        .invokeMethod<List<dynamic>>(Constants.listPairedDevices);
    return jsonDecode(device.toString())
        .map<Device>((json) => Device.fromJson(json))
        .toList();
  }

  @override
  void callPairedDevices() async {
    await methodChannel.invokeMethod(Constants.callPairedDevices);
  }

  @override
  void registerBroadcastReceiver() async {
    await methodChannel.invokeMethod(Constants.registerBroadcastReceiver);
  }

  @override
  void unregisterBroadcastReceiver() async {
    await methodChannel.invokeMethod(Constants.unregisterBroadcastReceiver);
  }

  /// BluetoothSocket */
  @override
  Future<void> initBluetoothSocket(String address, String uuid) async {
    await methodChannel.invokeMethod(Constants.initBluetoothSocket,
        {Constants.argumentAddress: address, Constants.argumentUuid: uuid});
  }

  @override
  Future<bool> isConnectBluetoothSocket() async {
    return await methodChannel.invokeMethod(Constants.isConnectBluetoothSocket);
  }

  @override
  void connectBluetoothSocket() async {
    await methodChannel.invokeMethod(Constants.connectBluetoothSocket);
  }

  @override
  void closeBluetoothSocket() async {
    await methodChannel.invokeMethod(Constants.closeBluetoothSocket);
  }

  @override
  Future<Uint8List> inputStreamBluetoothSocket() async {
    return await methodChannel
        .invokeMethod(Constants.inputStreamBluetoothSocket);
  }

  @override
  Future<void> outputStreamBluetoothSocket(Uint8List byte) async {
    await methodChannel.invokeMethod(Constants.outputStreamBluetoothSocket,
        {Constants.argumentOutputStream: byte});
  }
}
