import 'package:flutter/cupertino.dart';

import 'device_list_item.dart';

class DeviceListView {
  final DeviceListItem _todoListItem = DeviceListItem();

  Widget deviceListViewBuilder(context, snapshot, getDevice) {
    return ListView.builder(
      padding: const EdgeInsets.all(2.0),
      itemCount: snapshot.data.length,
      itemBuilder: (context, i) {
        return _todoListItem.deviceListItem(snapshot.data[i], getDevice);
      },
    );
  }
}
