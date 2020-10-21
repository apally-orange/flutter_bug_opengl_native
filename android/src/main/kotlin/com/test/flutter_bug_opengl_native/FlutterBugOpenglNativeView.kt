package com.test.flutter_bug_opengl_native

import android.content.Context
import android.view.View
import android.opengl.GLSurfaceView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class FlutterBugOpenglNativeView(private val context: Context) :PlatformView {
    private val glView = GLSurfaceView(context)

    init {
        glView.setRenderer(MyGLRenderer(context))
    }

    override fun getView(): View {
        return glView
    }

    override fun dispose() {}
}

