package com.backgroundstepcounter
import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule


@ReactModule(name = "BackgroundStepCounter")
abstract class BackgroundStepCounterSpec internal constructor(context: ReactApplicationContext) :
ReactContextBaseJavaModule(context) {

    @ReactMethod
    @DoNotStrip
    abstract fun isStepCountingSupported(promise: Promise)

    @ReactMethod
    @DoNotStrip
    abstract fun startStepCounterUpdate(from: Double)

    @ReactMethod
    @DoNotStrip
    abstract fun stopStepCounterUpdate()

    @ReactMethod
    @DoNotStrip
    abstract fun addListener(eventName: String)

    @ReactMethod
    @DoNotStrip
    abstract fun removeListeners(count: Double)
}