package com.test.flutter_bug_opengl_native

import android.content.Context
import android.app.Activity
import android.app.Application
import android.view.View
import android.os.Bundle
import android.opengl.GLSurfaceView
import com.visioglobe.visiomoveessential.VMEMapView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class FlutterBugOpenglNativeView(private val context: Context, private val activity: Activity) :PlatformView, Application.ActivityLifecycleCallbacks {
    //private val glView = GLSurfaceView(context)
    private val mapView = VMEMapView(context, null)
    private var disposed = false;

    init {
        activity.application.registerActivityLifecycleCallbacks(this)
        mapView.mapHash = "ma10a386909f58fb12f8bbde9c3111c46077f485d"
        mapView.promptUserToDownloadMap = false
        mapView.loadMap()
    }

    override fun getView(): View {
        return mapView
    }

    override fun dispose() {
        disposed = true
        mapView.unloadMap()
    }

     // Application.ActivityLifecycleCallbacks methods
    override fun onActivityResumed(activity: Activity) {
        if (disposed || activity.hashCode() != context.hashCode()) {
            return
        }
        mapView.onResume()
    }

    override fun onActivityPaused(activity: Activity) {
        if (disposed || activity.hashCode() != context.hashCode()) {
            return
        }
        mapView.onPause()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

}

