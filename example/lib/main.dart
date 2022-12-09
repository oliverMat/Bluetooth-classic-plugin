import 'package:bluetooth_classic/model/Device.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:bluetooth_classic/bluetooth_classic.dart';

import 'DeviceListView.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _bluetoothClassicPlugin = BluetoothClassic();

  final DeviceListView _deviceListView = DeviceListView();

  Future<List<Device>> _listDevices = Future(() => []);

  bool _checkPermission = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    try {
      _bluetoothClassicPlugin.registerBroadcastReceiver();
      _checkPermission = await _bluetoothClassicPlugin.checkPermission();
    } on PlatformException {
      _checkPermission = false;
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _checkPermission;
    });
  }

  Future<void> enableBluetooth() async {
    try {
      if (_checkPermission == true) {
        _bluetoothClassicPlugin.enableBluetooth();
      }
    } on PlatformException {}

    if (!mounted) return;

    setState(() {});
  }

  Future<void> loadDevices() async {
    try {
      _bluetoothClassicPlugin.startDeviceDiscovery();
      _bluetoothClassicPlugin.callPairedDevices();
      _listDevices = _bluetoothClassicPlugin.listPairedDevices();
    } on PlatformException {}

    if (!mounted) return;

    setState(() {
      _listDevices;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Row(
                children: [
                  ElevatedButton(
                      onPressed: _checkPermission ? null : enableBluetooth,
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.blue,
                        padding: const EdgeInsets.all(16),
                      ),
                      child: const Icon(
                        Icons.bluetooth,
                        size: 30,
                      )),
                  const SizedBox(width: 8),
                  ElevatedButton(
                      onPressed: () {
                        loadDevices();
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.blue,
                        padding: const EdgeInsets.all(16),
                      ),
                      child: const Icon(
                        Icons.change_circle_rounded,
                        size: 30,
                      )),
                ],
              ),
              const SizedBox(height: 16),
              Flexible(
                child: FutureBuilder<List>(
                    future: _listDevices,
                    initialData: const [],
                    builder: (context, snapshot) {
                      return snapshot.hasData
                          ? _deviceListView.deviceListViewBuilder(
                              context, snapshot, getDevice)
                          : _waiting();
                    }),
              ),
            ],
          ),
        )),
      ),
    );
  }

  Widget _waiting() {
    return const Center(
      child: Text("No data..."),
    );
  }

  void getDevice(Device device) {
    _bluetoothClassicPlugin.initBluetoothSocket(device.deviceHardwareAddress, "00001101-0000-1000-8000-00805F9B34FB");
  }
}
