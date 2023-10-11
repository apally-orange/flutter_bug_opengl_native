import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bug_opengl_native/bug_opengl_platform_interface.dart';

/// An implementation of [PluginAndroidPlatform] that uses method channels.
class MethodChannelMapView extends MapViewPlatform {
  static const _channelName = "test/channel";
  int channelId = 0;

  void test() {
    final channel = MethodChannel("$_channelName$channelId");
    channel.invokeMethod("test");
  }

  @override
  Widget buildView() {
    if (Platform.isAndroid) {
      return PlatformViewLink(
        viewType: _channelName,
        surfaceFactory: (
          BuildContext context,
          PlatformViewController controller,
        ) {
          return AndroidViewSurface(
            controller: controller as AndroidViewController,
            gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
          );
        },
        onCreatePlatformView: (PlatformViewCreationParams params) {
          final AndroidViewController controller =
              PlatformViewsService.initExpensiveAndroidView(
            id: params.id,
            viewType: _channelName,
            layoutDirection: TextDirection.ltr,
            creationParamsCodec: const StandardMessageCodec(),
            onFocus: () => params.onFocusChanged(true),
          );
          controller.addOnPlatformViewCreatedListener(
            params.onPlatformViewCreated,
          );

          print("DEBUG onCreatePlatformView");
          channelId = params.id;

          return controller;
        },
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
