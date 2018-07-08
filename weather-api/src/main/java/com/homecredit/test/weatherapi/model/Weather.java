package com.homecredit.test.weatherapi.model;

public class Weather {

	private String location;
	private String weather;
	private String temperature;

	public Weather(String location, String weather, String temperature) {
		super();
		this.location = location;
		this.weather = weather;
		this.temperature = temperature;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
