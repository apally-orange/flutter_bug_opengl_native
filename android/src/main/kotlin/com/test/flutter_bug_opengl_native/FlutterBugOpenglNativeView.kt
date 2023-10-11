package com.test.flutter_bug_opengl_native

import android.content.Context
import android.app.Activity
import android.app.Application
import android.view.View
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import org.json.JSONObject
import android.opengl.GLSurfaceView
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import com.visioglobe.visiomoveessential.enums.VMELocationTrackingMode
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType
import com.visioglobe.visiomoveessential.enums.VMEViewMode
import com.visioglobe.visiomoveessential.models.*
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class FlutterBugOpenglNativeView(private val id: Int, private val context: Context, private val messenger: BinaryMessenger, private val activity: Activity) :PlatformView,  MethodChannel.MethodCallHandler, Application.ActivityLifecycleCallbacks {
    //private val glView = GLSurfaceView(context)
    private val mapView = VMEMapView(context, null)
    private var disposed = false
    private lateinit var mapController: VMEMapController
    private val methodChannel: MethodChannel = MethodChannel(messenger, "$CHANNEL$id")
    private val visioglobeLifeCycleListener = VisioglobeLifeCycleListener()

    companion object {
        const val CHANNEL = "test/channel"
    }

    init {
        methodChannel.setMethodCallHandler(this)
        activity.application.registerActivityLifecycleCallbacks(this)
        
        val builder = VMEMapControllerBuilder()
        builder.mapHash = "m05e57ee028b117e658a07d8b1a4bf0e0f8247bb2"
        mapController = VMEMapController(context, builder)
        mapController.setLifeCycleListener(visioglobeLifeCycleListener)
        mapController.loadMapView(mapView)
        mapController.loadMapData()
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.i("DEBUG", "Method channel call")
        
        GlobalScope.launch(Dispatchers.Default) {
            updateColor()
        }
    }

    private suspend fun updateColor() {
        val defaultColor: Int = 0x00FF11
        var placesColor = hashMapOf<String, Int>()
        allPois.forEach { 
            placesColor[it] = defaultColor
        }

        withContext(Dispatchers.Main) {
            mapController.setPoisColor(placesColor)

            val cameraUpdate = VMECameraUpdateBuilder()
                    .setTargets(listOf("B2-UL01-ID0044"))
                    .setViewMode(VMEViewMode.FLOOR)
                    .setDistanceRange(VMECameraDistanceRange.newAltitudeRange(
                        10.0,
                        100.0))
                    .build()
            mapController.animateCamera(cameraUpdate, 0.3f, null)
        }
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
            Log.i("DEBUG", "mapDidInitializeEngine")
        }

        override fun mapDataDidLoad(mapVenueInfo: JSONObject) {
            super.mapDataDidLoad(mapVenueInfo)
            Log.i("DEBUG", "mapDataDidLoad")
            mapController.loadMapView(mapView)
        }

        override fun mapViewDidLoad() {
            super.mapViewDidLoad()
            Log.i("DEBUG", "mapViewDidLoad")
        }

        override fun mapDidGainFocus() {
            super.mapDidGainFocus()
        }
    }

    private val allPois = listOf("MeetingRoom","Room2-B","Cafe","Room1","Room2-A","Room5","Room6","Room7","Room8","B1-1-1","B1-1-4","B1-1-5","B1-1-6","B1-1-7","B1-1-8","B1-2-1","B1-2-2","B1-2-3","B1-2-4","B1-2-5","B1-2-6","B1-2-7","B1-2-8","B1-3-1","B1-3-2","B1-3-3","B1-3-4","B1-3-5","B1-3-6","B1-3-7","B1-3-8","B1-4-1","B1-4-2","B1-4-3","B1-4-4","B1-4-5","B1-4-6","B1-4-7","B1-4-8","B2-0-1","B2-0-2","B2-0-3","B2-0-4","B2-0-5","B2-0-6","B2-0-7","B2-0-8","B2-1-1","B2-1-2","B2-1-3","B2-1-4","B2-1-5","B2-1-6","B2-1-7","B2-1-8","B2-2-1","B2-2-2","B2-2-3","B2-2-4","B2-2-5","B2-2-6","B2-2-7","B2-2-8","Room2-B")
}

