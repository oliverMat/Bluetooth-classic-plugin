class Device {
  final String name;
  final String deviceHardwareAddress;
  final bool paired;

  const Device({
    required this.name,
    required this.deviceHardwareAddress,
    required this.paired
  });

  factory Device.fromJson(Map<String, dynamic> json) {
    return Device(
      name: json['name'] as String,
      deviceHardwareAddress: json['deviceHardwareAddress'] as String,
      paired: json['paired'] as bool,
    );
  }
}
