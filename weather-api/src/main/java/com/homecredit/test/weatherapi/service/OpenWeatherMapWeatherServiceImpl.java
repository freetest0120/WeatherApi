package com.homecredit.test.weatherapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.homecredit.test.weatherapi.dao.WeatherApiDao;
import com.homecredit.test.weatherapi.model.Locations;
import com.homecredit.test.weatherapi.model.OWMWeather;
import com.homecredit.test.weatherapi.model.OWMWeatherResponse;
import com.homecredit.test.weatherapi.model.Weather;
import com.homecredit.test.weatherapi.model.WeatherLog;

@Service
public class OpenWeatherMapWeatherServiceImpl implements WeatherService {

	@Value("${serviceURL}")
	private String serviceURL;

	@Value("${appID}")
	private String APPID;

	@Autowired
	private WeatherApiDao weatherApiDao;

	private RestTemplate restTemplate = new RestTemplate();

	public List<Weather> getWeather() {

		ResponseEntity<OWMWeatherResponse> responseEntity = restTemplate.getForEntity(
				serviceURL + "group?id=" + Locations.getAllId() + "&units=metric&appid=" + APPID,
				OWMWeatherResponse.class);

		ArrayList<Weather> list = new ArrayList<Weather>();

		for (OWMWeather ws : responseEntity.getBody().getList()) {
			Weather w = new Weather(ws.getName(), ws.getWeatherValue(), ws.getMain().getTemp());
			list.add(w);
		}

		return list;
	}

	public Weather getWeather(int id) {

		Locations loc = Locations.valueof(id);

		if (loc != null) {

			ResponseEntity<OWMWeather> responseEntity = restTemplate.getForEntity(
					serviceURL + "weather?q=" + Locations.valueof(id) + "&units=metric&appid=" + APPID,
					OWMWeather.class);

			OWMWeather owmWeater = responseEntity.getBody();

			Weather weather = new Weather(owmWeater.getName(), owmWeater.getWeatherValue(),
					owmWeater.getMain().getTemp());

			weatherApiDao.insertWeatherLog(java.util.UUID.randomUUID().toString(), weather.getLocation(),
					weather.getWeather(), weather.getWeather());

			return weather;
		}

		return null;
	}

	public List<WeatherLog> getWeatherLog() {

		return weatherApiDao.getRecentWeatherLog(5);
	}

}
