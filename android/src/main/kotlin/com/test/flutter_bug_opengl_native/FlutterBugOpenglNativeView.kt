package com.test.flutter_bug_opengl_native

import android.content.Context
import android.view.View
import android.opengl.GLSurfaceView
import com.visioglobe.visiomoveessential.VMEMapView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class FlutterBugOpenglNativeView(private val context: Context) :PlatformView {
    //private val glView = GLSurfaceView(context)
    private val mapView = VMEMapView(context, null)

    init {
        //glView.setRenderer(MyGLRenderer(context))
        mapView.mapHash = "ma10a386909f58fb12f8bbde9c3111c46077f485d"
        mapView.promptUserToDownloadMap = false
        mapView.loadMap()
    }

    override fun getView(): View {
        return mapView
    }

    override fun dispose() {
        mapView.unloadMap()
    }
}

