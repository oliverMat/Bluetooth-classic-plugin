class Device {

  Device(this.name, this.deviceHardwareAddress, this.paired);

  String name = "";
  String deviceHardwareAddress = "";
  bool paired = false;

  Device.fromJson(Map<List<String>, dynamic> json)
      : name = json['name'] as String,
        deviceHardwareAddress = json['deviceHardwareAddress'] as String,
        paired = json["paired"] as bool;

}