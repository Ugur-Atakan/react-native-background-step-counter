# Android basic sensor & composite sensor

- [Android Motion sensor](https://developer.android.com/guide/topics/sensors/sensors_motion)
- [Android sensor type](https://source.android.com/docs/core/interaction/sensors/sensor-types)

# Android Basic & Composite Sensors

## TYPE_ACCELEROMETER

`SensorEvent.values[0]`: Acceleration along the x-axis (including gravity). m/s²  
`SensorEvent.values[1]`: Acceleration along the y-axis (including gravity). m/s²  
`SensorEvent.values[2]`: Acceleration along the z-axis (including gravity). m/s²  

## TYPE_ACCELEROMETER_UNCALIBRATED

`SensorEvent.values[0]`: Uncalibrated acceleration along the x-axis. m/s²  
`SensorEvent.values[1]`: Uncalibrated acceleration along the y-axis. m/s²  
`SensorEvent.values[2]`: Uncalibrated acceleration along the z-axis. m/s²  
`SensorEvent.values[3]`: Bias compensated acceleration along the x-axis. m/s²  
`SensorEvent.values[4]`: Bias compensated acceleration along the y-axis. m/s²  
`SensorEvent.values[5]`: Bias compensated acceleration along the z-axis. m/s²  

## TYPE_GRAVITY

`SensorEvent.values[0]`: Gravity on the x-axis. m/s²  
`SensorEvent.values[1]`: Gravity on the y-axis. m/s²  
`SensorEvent.values[2]`: Gravity on the z-axis. m/s²  

## TYPE_GYROSCOPE

`SensorEvent.values[0]`: Angular velocity around the x-axis. rad/s  
`SensorEvent.values[1]`: Angular velocity around the y-axis. rad/s  
`SensorEvent.values[2]`: Angular velocity around the z-axis. rad/s  

## TYPE_GYROSCOPE_UNCALIBRATED

`SensorEvent.values[0]`: Uncalibrated angular velocity around the x-axis. rad/s  
`SensorEvent.values[1]`: Uncalibrated angular velocity around the y-axis. rad/s  
`SensorEvent.values[2]`: Uncalibrated angular velocity around the z-axis. rad/s  
`SensorEvent.values[3]`: Estimated drift around the x-axis. rad/s  
`SensorEvent.values[4]`: Estimated drift around the y-axis. rad/s  
`SensorEvent.values[5]`: Estimated drift around the z-axis. rad/s  

## TYPE_LINEAR_ACCELERATION

`SensorEvent.values[0]`: Acceleration along the x-axis (excluding gravity). m/s²  
`SensorEvent.values[1]`: Acceleration along the y-axis (excluding gravity). m/s²  
`SensorEvent.values[2]`: Acceleration along the z-axis (excluding gravity). m/s²  

## TYPE_ROTATION_VECTOR

`SensorEvent.values[0]`: x-axis component of the rotation vector (x * sin(θ/2)). Unitless  
`SensorEvent.values[1]`: y-axis component of the rotation vector (y * sin(θ/2)). Unitless  
`SensorEvent.values[2]`: z-axis component of the rotation vector (z * sin(θ/2)). Unitless  
`SensorEvent.values[3]`: Scalar component of the rotation vector (cos(θ/2)). Optional. Unitless  

## TYPE_STEP_COUNTER

`SensorEvent.values[0]`: Number of steps taken by the user since the last reboot while the sensor was activated. Unitless  

### TYPE_SIGNIFICANT_MOTION

(Detects significant or potentially dangerous activity) None

### TYPE_STEP_DETECTOR

None
