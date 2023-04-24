package com.test.flutter_bug_opengl_native;

import android.app.Activity;
import io.flutter.plugin.platform.PlatformView;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;

public class FlutterBugOpenglNativeViewJava implements PlatformView, Application.ActivityLifecycleCallbacks {

    private VMEMapView mapView;
    private VMEMapController mapController;

    public FlutterBugOpenglNativeViewJava(Context context, Activity activity) {
        mapView = new VMEMapView(context, null);
        VMEMapControllerBuilder builder = new VMEMapControllerBuilder();
        builder.setMapHash("mc8f3fec89d2b7283d15cfcf4eb28a0517428f054");
        mapController = new VMEMapController(context, builder);
        mapController.setLifeCycleListener(lifeCycleListener);
        mapController.loadMapData();
    }

    public View getView() {
        return mapView;
    }

    public void dispose() {
    }

    // Application.ActivityLifecycleCallbacks methods
    public void onActivityResumed(Activity activity) {
        if (activity.hashCode() != mapView.getContext().hashCode()) {
            return;
        }
        mapController.onResume();
    }

    public void onActivityPaused(Activity activity) {
        if (activity.hashCode() != mapView.getContext().hashCode()) {
            return;
        }
        mapController.onPause();
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    private final VMELifeCycleListener lifeCycleListener = new VMELifeCycleListener()
    {
        @Override
        public void mapDidInitializeEngine() {
            /*startCameraPosition();*/
        }

        @Override
        public void mapDataDidLoad() {
            super.mapDataDidLoad();
            mapController.loadMapView(mapView);
        }

        @Override
        public void mapViewDidLoad() {
        }
    };
}

