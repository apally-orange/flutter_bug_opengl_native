package com.test.flutter_bug_opengl_native_example

import io.flutter.embedding.android.FlutterActivity
 
import android.os.Bundle
import io.flutter.embedding.android.FlutterTextureView
 
class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        intent.putExtra("background_mode", "transparent")
        super.onCreate(savedInstanceState)
    }
 
    override fun onFlutterTextureViewCreated(flutterTextureView: FlutterTextureView) {
        flutterTextureView.isOpaque = true
        super.onFlutterTextureViewCreated(flutterTextureView)
    }
}