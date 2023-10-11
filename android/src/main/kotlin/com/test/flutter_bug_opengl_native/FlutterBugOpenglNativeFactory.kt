package com.test.flutter_bug_opengl_native

import android.content.Context
import android.app.Activity
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class FlutterBugOpenglNativeFactory(private val messenger: BinaryMessenger, private val activity: Activity): PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context?, id: Int, args: Any?): PlatformView {
        return FlutterBugOpenglNativeView(id, context!!, messenger, activity)
    }
}