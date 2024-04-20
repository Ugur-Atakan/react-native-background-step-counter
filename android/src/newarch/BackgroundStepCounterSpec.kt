package com.backgroundstepcounter

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext

abstract class BackgroundStepCounterSpec internal constructor(context: ReactApplicationContext) :
NativeBackgroundStepCounterSpec(context) {

    override fun getName(): String = "BackgroundStepCounter"

    abstract override fun isStepCountingSupported(promise: Promise)

    abstract override fun startStepCounterUpdate()

    abstract override fun stopStepCounterUpdate()

    override fun addListener(eventName: String) {
    }

    override fun removeListeners(count: Double) {
    }
}