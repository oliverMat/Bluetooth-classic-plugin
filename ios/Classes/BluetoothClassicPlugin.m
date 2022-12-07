#import "BluetoothClassicPlugin.h"
#if __has_include(<bluetooth_classic/bluetooth_classic-Swift.h>)
#import <bluetooth_classic/bluetooth_classic-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "bluetooth_classic-Swift.h"
#endif

@implementation BluetoothClassicPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBluetoothClassicPlugin registerWithRegistrar:registrar];
}
@end
