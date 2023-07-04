package com.test.flutter_bug_opengl_native

import android.content.Context
import android.app.Activity
import android.app.Application
import android.view.View
import android.os.Bundle
import org.json.JSONObject
import android.opengl.GLSurfaceView
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class FlutterBugOpenglNativeView(private val context: Context, private val activity: Activity) :PlatformView, Application.ActivityLifecycleCallbacks {
    //private val glView = GLSurfaceView(context)
    private val mapView = VMEMapView(context, null)
    private var disposed = false
    private lateinit var mapController: VMEMapController
    private val visioglobeLifeCycleListener = VisioglobeLifeCycleListener()

    init {
        activity.application.registerActivityLifecycleCallbacks(this)
        
        val builder = VMEMapControllerBuilder()
        builder.mapHash = "mc8f3fec89d2b7283d15cfcf4eb28a0517428f054"
        mapController = VMEMapController(context, builder)
        mapController.setLifeCycleListener(visioglobeLifeCycleListener)
        mapController.loadMapView(mapView)
        mapController.loadMapData()
    }

    override fun getView(): View {
        return mapView
    }

    override fun dispose() {
        disposed = true
        mapController.dispose()
    }

     // Application.ActivityLifecycleCallbacks methods
    override fun onActivityResumed(activity: Activity) {
        if (disposed || activity.hashCode() != context.hashCode()) {
            return
        }
        mapController.onResume()
    }

    override fun onActivityPaused(activity: Activity) {
        if (disposed || activity.hashCode() != context.hashCode()) {
            return
        }
        mapController.onPause()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    inner class VisioglobeLifeCycleListener : VMELifeCycleListener() {
        override fun mapDidInitializeEngine() {
           
        }

        override fun mapDataDidLoad(mapVenueInfo: JSONObject) {
            super.mapDataDidLoad(mapVenueInfo)
            mapController.loadMapView(mapView)
        }

        override fun mapViewDidLoad() {
            super.mapViewDidLoad()
        }

        override fun mapDidGainFocus() {
            super.mapDidGainFocus()
        }
    }

}