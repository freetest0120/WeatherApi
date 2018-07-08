package com.homecredit.test.weatherapi.dao;

import java.util.List;

import com.homecredit.test.weatherapi.model.Weather;
import com.homecredit.test.weatherapi.model.WeatherLog;

public interface WeatherApiDao {
	
	int insertWeatherLog(String responseId, String location, String actualWeather,
			String temperature);
	
	List<WeatherLog> getRecentWeatherLog(int count);

}
