import 'package:bluetooth_classic/model/Device.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:bluetooth_classic/bluetooth_classic.dart';

import 'device_list_view.dart';

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

  @override
  void initState() {
    super.initState();
    initPlatformState();
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
                  const Text(
                    'checkPermission :',
                  ),
                  StreamBuilder<bool>(
                    stream: _bluetoothClassicPlugin.isPermissionsGranted(),
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return Text(
                          '${snapshot.data}',
                          style: Theme.of(context).textTheme.headline4,
                        );
                      } else {
                        return CircularProgressIndicator();
                      }
                    },
                  ),
                  const Text(
                    'isEnableBluetooth :',
                  ),
                  StreamBuilder<bool>(
                    stream: _bluetoothClassicPlugin.isEnableBluetooth(),
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return Text(
                          '${snapshot.data}',
                          style: Theme.of(context).textTheme.headline4,
                        );
                      } else {
                        return CircularProgressIndicator();
                      }
                    },
                  ),
                  const Text(
                    'isConnectBluetoothSocket :',
                  ),
                  StreamBuilder<bool>(
                    stream: _bluetoothClassicPlugin.isConnectBluetoothSocket(),
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return Text(
                          '${snapshot.data}',
                          style: Theme.of(context).textTheme.headline4,
                        );
                      } else {
                        return CircularProgressIndicator();
                      }
                    },
                  ),
                  Row(
                    children: [
                      ElevatedButton(
                          onPressed: () {
                            enableBluetooth();
                            setState(() {

                            });
                          } ,
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
                      const SizedBox(width: 8),
                      ElevatedButton(
                          onPressed: () {
                            _bluetoothClassicPlugin.closeBluetoothSocket();
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.blue,
                            padding: const EdgeInsets.all(16),
                          ),
                          child: const Icon(
                            Icons.close,
                            size: 30,
                          )),
                      const SizedBox(width: 8),
                      ElevatedButton(
                          onPressed: () {
                            setState(() {

                            });
                            outputStream();
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.blue,
                            padding: const EdgeInsets.all(16),
                          ),
                          child: const Icon(
                            Icons.send,
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

  Future<void> initPlatformState() async {
    try {
      _bluetoothClassicPlugin.initBluetoothAdapter();
    } on PlatformException {
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
    });
  }

  Future<void> enableBluetooth() async {
    try {
      _bluetoothClassicPlugin.requirePermission();
    } catch (e) {
      e.toString();
    }

    if (!mounted) return;

    setState(() {});
  }

  Future<void> loadDevices() async {
    try {
      //_btFlutterPlugin.startDeviceDiscovery();
      _bluetoothClassicPlugin.callPairedDevices();
      _listDevices = _bluetoothClassicPlugin.listPairedDevices();
    } catch (e) {
      e.toString();
    }

    if (!mounted) return;

    setState(() {
      _listDevices;
    });
  }

  void getDevice(Device device) {
    try {
      _bluetoothClassicPlugin.initBluetoothSocket(
          device.deviceHardwareAddress, "00001101-0000-1000-8000-00805F9B34FB");
      _bluetoothClassicPlugin.connectBluetoothSocket();
    } catch (e) {
      e.toString();
    }
  }

  Future<void> outputStream() async {
    try {
      _bluetoothClassicPlugin.outputStreamBluetoothSocket(
          Uint8List.fromList([0x80, 0x00, 0x00, 0x80]));
      receiver();
    } catch (e) {
      e.toString();
    }
  }

  Future<void> receiver() async {
    try {
      Uint8List unit8Lis = await _bluetoothClassicPlugin.inputStreamBluetoothSocket();
      print(unit8Lis.toList());
    } catch (e) {
      e.toString();
    }
  }
}
