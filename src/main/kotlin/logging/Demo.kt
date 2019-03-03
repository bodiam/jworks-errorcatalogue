package logging

import logging.ErrorMessage.CITY_NOT_SUPPORTED
import logging.ErrorMessage.WEATHER_LOADING_ERROR
import org.slf4j.Logger
import org.slf4j.LoggerFactory


data class Weather(val city: String, val temperature: Double)
class WeatherException(error: ErrorMessage) : Exception(error.toString())

class WeatherService {

    fun findWeather(city: String): Weather {
        return if (city == "Sydney") {
            Weather(city, temperature = 25.9)
        } else {
            throw WeatherException(CITY_NOT_SUPPORTED)
        }
    }
}

class WeatherController(private val service: WeatherService = WeatherService()) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun weather(city : String) {
        try {
            service.findWeather(city)
        } catch (we: WeatherException) {
            log.error(WEATHER_LOADING_ERROR, city, we)
        }
    }
}

enum class ErrorMessage(val code: String, val message: String) {
    WEATHER_LOADING_ERROR("WS0001", "Couldn't load weather for city of {}"),
    CITY_NOT_SUPPORTED("WS0002", "City not supported");

    override fun toString(): String = "$code: $message"
}

fun org.slf4j.Logger.error(message: ErrorMessage, args: Any, exception: Exception) {
    error(message.toString(), args, exception)
}

fun main() {
    WeatherController().weather("Melbourne")
}