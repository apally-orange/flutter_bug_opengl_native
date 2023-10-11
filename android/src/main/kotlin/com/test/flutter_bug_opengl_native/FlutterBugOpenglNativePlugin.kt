package com.test.flutter_bug_opengl_native

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry.Registrar


/** FlutterBugOpenglNativePlugin */
public class FlutterBugOpenglNativePlugin():
          FlutterPlugin,
          ActivityAware {

    private lateinit var flutterPluginBinding: FlutterPluginBinding

    override fun onAttachedToEngine( binding: FlutterPlugin.FlutterPluginBinding) {
      this.flutterPluginBinding = binding
    }

    override fun onDetachedFromEngine( binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onAttachedToActivity( binding: ActivityPluginBinding) {
      flutterPluginBinding
              .platformViewRegistry
              .registerViewFactory(
                      FlutterBugOpenglNativeView.CHANNEL,
                      FlutterBugOpenglNativeFactory(flutterPluginBinding.binaryMessenger, binding.getActivity()))
    }

    override fun onDetachedFromActivity() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
      onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivityForConfigChanges() {
      onDetachedFromActivity()
    }
  }
