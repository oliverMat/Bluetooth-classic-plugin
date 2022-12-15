import 'package:bluetooth_classic/model/Device.dart';
import 'package:flutter/material.dart';

class DeviceListItem {
  Widget deviceListItem(Device device, getDevice) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 2),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          color: Colors.grey[200],
        ),
        padding: const EdgeInsets.all(16),
        child: Row(
          children: [
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Text(
                    device.name,
                    style: const TextStyle(
                        fontSize: 16, fontWeight: FontWeight.w600),
                  ),
                  Text(
                    device.deviceHardwareAddress,
                    style: const TextStyle(fontSize: 12),
                  ),
                ],
              ),
            ),
            ElevatedButton(
              onPressed: () {
                if (device.paired) {
                  getDevice(device);
                } else {
                  null;
                }
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blueGrey,
              ),
              child: Icon(
                device.paired ? Icons.link : Icons.link_off,
                size: 30,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
