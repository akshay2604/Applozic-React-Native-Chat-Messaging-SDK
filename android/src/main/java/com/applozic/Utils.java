package com.applozic;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule;


import javax.annotation.Nullable;

public class Utils {
  private static final String TAG = "Utils";

 /**
   * send a JS event
  **/
  public static void sendEvent(final ReactContext context, final String eventName, Object body) {
    if (context != null) {
      context
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, body);
    } else {
      Log.d(TAG, "Missing context - cannot send event!");
    }
  }
}