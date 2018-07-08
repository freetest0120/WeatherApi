package com.homecredit.test.weatherapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homecredit.test.weatherapi.ResponseMessage;
import com.homecredit.test.weatherapi.model.Weather;
import com.homecredit.test.weatherapi.model.WeatherLog;
import com.homecredit.test.weatherapi.service.WeatherService;

@RestController
@RequestMapping("/api")
public class WeatherApiController {

	public static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiController.class);

	@Autowired
	private WeatherService weatherApiService;

	@RequestMapping(value = "/weather/", method = RequestMethod.GET)
	public ResponseEntity<List<Weather>> getWeatherNow() {
		LOGGER.info("Request type: GET");
		List<Weather> weather = weatherApiService.getWeather();

		if (weather.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Weather>>(weather, HttpStatus.OK);
	}

	@RequestMapping(value = "/weather/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getWeatherNowFor(@PathVariable("id") int id) {
		LOGGER.info("Request type: GET for id {}", id);
		Weather weather = weatherApiService.getWeather(id);

		if (weather == null) {
			return new ResponseEntity(new ResponseMessage("Location not fourd"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Weather>(weather, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/weather/recent", method = RequestMethod.GET)
	public ResponseEntity<List<WeatherLog>> getWeatherLog() {
		LOGGER.info("Request type: GET");
		List<WeatherLog> weatherLog = weatherApiService.getWeatherLog();

		if (weatherLog.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<WeatherLog>>(weatherLog, HttpStatus.OK);
	}

}
