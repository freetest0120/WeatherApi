package com.homecredit.test.weatherapi.service;

import java.util.List;

import com.homecredit.test.weatherapi.model.Weather;
import com.homecredit.test.weatherapi.model.WeatherLog;

public interface WeatherService {

	List<Weather> getWeather();

	List<WeatherLog> getWeatherLog();

	Weather getWeather(int id);

}
