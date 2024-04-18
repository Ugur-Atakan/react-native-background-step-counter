package com.backgroundstepcounter
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter
import com.backgroundstepcounter.services.AccelerometerService
import com.backgroundstepcounter.services.BackgroundService
import com.backgroundstepcounter.services.SensorListenService
import com.backgroundstepcounter.services.StepCounterService
import com.backgroundstepcounter.utils.AndroidVersionHelper


class BackgroundStepCounterModule internal constructor(context: ReactApplicationContext) :
BackgroundStepCounterSpec(context) {
    companion object {
        const val NAME: String = "BackgroundStepCounter"
        private val TAG_NAME: String = BackgroundStepCounterModule::class.java.name
        private const val STEP_COUNTER = "android.permission.ACTIVITY_RECOGNITION"
    }

    private val appContext: ReactApplicationContext = context
    private val  sensorManager: SensorManager
    private val stepsOK: Boolean
        get() = checkSelfPermission(appContext, STEP_COUNTER) == PERMISSION_GRANTED
    private val accelOK: Boolean
        get() = AndroidVersionHelper.isHardwareAccelerometerEnabled(appContext)
    private val supported: Boolean
        get() = AndroidVersionHelper.isHardwareStepCounterEnabled(appContext)
    private val walkingStatus: Boolean
        get() = stepCounterListener !== null

    /**
     * gets the step counter listener
     * @return the step counter listener
     * @see SensorListenService
     * @see StepCounterService
     * @see AccelerometerService
     * @see checkSelfPermission
     * @see PERMISSION_GRANTED
     */
    private var stepCounterListener: SensorListenService? = null

    /**
     * The method that is called when the module is initialized.
     * It checks the permission and the availability for the step counter sensor and initializes the step counter service.
     */
    init {
        sensorManager = context.getSystemService(
                Context.SENSOR_SERVICE
        ) as SensorManager
        stepCounterListener = if (stepsOK) {
            StepCounterService(this, sensorManager)
        } else {
            AccelerometerService(this, sensorManager)
        }
        appContext.addLifecycleEventListener(stepCounterListener)
    }

    /**
     * The method ask if the step counter sensor is supported.
     * @param promise the promise that is used to return the result to the react-native code
     * @see Promise.resolve
     * @see VERSION_CODES.ECLAIR
     * @see VERSION_CODES.KITKAT
     * @see WritableMap
     */
    @ReactMethod
     fun isStepCountingSupported(promise: Promise) {
        Log.d(TAG_NAME, "hardware_step_counter? $supported")
        Log.d(TAG_NAME, "step_counter granted? $stepsOK")
        Log.d(TAG_NAME, "accelerometer granted? $accelOK")
        sendDeviceEvent("stepDetected", walkingStatus)
        promise.resolve(
                Arguments.createMap().apply {
                    putBoolean("supported", supported)
                    putBoolean("granted", stepsOK || accelOK)
                    putBoolean("working", walkingStatus)
                }
        )
    }

    /**
     * Start the step counter sensor.
     * @param from the number of steps to start from
     */
    @ReactMethod
     fun startStepCounterUpdate(from: Double) {
        stepCounterListener = stepCounterListener ?: if (stepsOK) {
            StepCounterService(this, sensorManager)
        } else {
            AccelerometerService(this, sensorManager)
        }
        Log.d(TAG_NAME, "startStepCounterUpdate")
        stepCounterListener!!.startService()
    }

    /**
     * Stop the step counter sensor.
     * @return Nothing.
     */
    @ReactMethod
     fun stopStepCounterUpdate() {
        Log.d(TAG_NAME, "stopStepCounterUpdate")
        stepCounterListener!!.stopService()
    }

    @ReactMethod
    fun startBackgroundService() {
        val serviceIntent = Intent(reactApplicationContext, BackgroundService::class.java)
        reactApplicationContext.startForegroundService(serviceIntent)
    }

    @ReactMethod
    fun stopBackgroundService() {
        val serviceIntent = Intent(reactApplicationContext, BackgroundService::class.java)
        reactApplicationContext.stopService(serviceIntent)
    }


    /**
     * Keep: Required for RN built in Event Emitter Support.
     * @param eventName the name of the event. usually "stepCounterUpdate".
     */
    @ReactMethod
     fun addListener(eventName: String) {}

    /**
     * Keep: Required for RN built in Event Emitter Support.
     * @param count the number of listeners to remove.
     * not implemented.
     */
    @ReactMethod
     fun removeListeners(count: Double) {}

    /**
     * StepCounterPackage requires this property for the module.
     * @return the name of the module. usually "StepCounter".
     */
    override fun getName(): String = NAME

    /**
     * Send the step counter update event to the react-native code.
     * @param eventPayload the object that contains information about the step counter update.
     * @return Nothing.
     * @see WritableMap
     * @see RCTDeviceEventEmitter
     * @see com.facebook.react.modules.core.DeviceEventManagerModule
     * @throws RuntimeException if the event emitter is not initialized.
     */
    fun sendDeviceEvent(eventType: String, eventPayload: Any) {
        try {
            appContext.getJSModule(RCTDeviceEventEmitter::class.java)
                    .emit("$NAME.$eventType", eventPayload)
        } catch (e: RuntimeException) {
            e.message?.let { Log.e(TAG_NAME, it) }
            Log.e(TAG_NAME, eventType, e)
        }
    }
}
