import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bug_opengl_native/bug_opengl_platform_interface.dart';

/// An implementation of [PluginAndroidPlatform] that uses method channels.
class MethodChannelMapView extends MapViewPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('plugin_android');

  @override
  Widget buildView() {
    if (Platform.isAndroid) {
      return AndroidView(
        viewType: "test/channel",
        creationParamsCodec: const StandardMessageCodec(),
      );
    } else {
      return const Center(
        child: Text(
          'Not implemented on this OS',
        ),
      );
    }
  }
}
