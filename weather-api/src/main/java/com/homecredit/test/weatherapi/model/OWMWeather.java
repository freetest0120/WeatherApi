package com.homecredit.test.weatherapi.model;

import java.util.List;

public class OWMWeather {

	private String name;
	private Main main;
	private List<WeatherCondition> weather;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public List<WeatherCondition> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherCondition> weather) {
		this.weather = weather;
	}

	public String getWeatherValue() {
		String value = "";
		for (WeatherCondition wc : weather)
			value += wc.getDescription() + ", ";

		return value.replaceAll(", $", "");
	}

	public static class Main {
		private String temp;

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}
	}

	public static class WeatherCondition {

		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
