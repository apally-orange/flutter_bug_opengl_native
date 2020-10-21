#import "FlutterBugOpenglNativePlugin.h"
#if __has_include(<flutter_bug_opengl_native/flutter_bug_opengl_native-Swift.h>)
#import <flutter_bug_opengl_native/flutter_bug_opengl_native-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_bug_opengl_native-Swift.h"
#endif

@implementation FlutterBugOpenglNativePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterBugOpenglNativePlugin registerWithRegistrar:registrar];
}
@end
