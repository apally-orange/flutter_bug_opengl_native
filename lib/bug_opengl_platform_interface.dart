import 'package:flutter/material.dart';
import 'package:flutter_bug_opengl_native/bug_opengl_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

abstract class MapViewPlatform extends PlatformInterface {
  MapViewPlatform() : super(token: _token);

  static final Object _token = Object();

  static MapViewPlatform _instance = MethodChannelMapView();

  static MapViewPlatform get instance => _instance;

  static set instance(MapViewPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  void test();

  Widget buildView() {
    throw UnimplementedError(
      'buildView() has not been implemented.',
    );
  }
}
