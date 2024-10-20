package com.core.weather_utils


/**
 * Object responsible for formatting various weather-related data such as temperature,
 * humidity, wind speed, and atmospheric pressure.
 */
object WeatherFormatter {

    /**
     * Formats temperature into a string with 1 decimal place, followed by the °C symbol.
     *
     * @param temp The temperature value in degrees Celsius.
     * @return A string representation of the temperature, e.g., "25.3°C".
     */
    fun formatTemperature(temp: Double): String {
        return String.format("%.1f°C", temp)
    }

    /**
     * Formats humidity as a percentage string.
     *
     * @param humidity The humidity value as a percentage (0-100).
     * @return A string representation of the humidity, e.g., "80%".
     */
    fun formatHumidity(humidity: Int): String {
        return "$humidity%"
    }

    /**
     * Formats wind speed into a string with 1 decimal place, followed by "m/s".
     *
     * @param speed The wind speed value in meters per second.
     * @return A string representation of the wind speed, e.g., "5.4 km/h".
     */
    fun formatWindSpeed(speed: Double): String {
        return String.format("%.1f km/h", speed)
    }

    /**
     * Formats atmospheric pressure into a string, followed by "hPa" (hectopascal).
     *
     * @param pressure The atmospheric pressure value in hectopascals (hPa).
     * @return A string representation of the pressure, e.g., "1013 mb".
     */
    fun formatPressure(pressure: Double): String {
        return String.format("%.0f mb", pressure)
    }
}