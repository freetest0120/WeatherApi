package com.homecredit.test.weatherapi.model;

public class WeatherLog {

	private int id;
	private String responseId;
	private String location;
	private String actualWeather;
	private String temperature;
	private String dtimeInserted;

	public WeatherLog(int id, String responseId, String location, String actualWeather, String temperature,
			String dtimeInserted) {
		super();
		this.id = id;
		this.responseId = responseId;
		this.location = location;
		this.actualWeather = actualWeather;
		this.temperature = temperature;
		this.dtimeInserted = dtimeInserted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getActualWeather() {
		return actualWeather;
	}

	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDtimeInserted() {
		return dtimeInserted;
	}

	public void setDtimeInserted(String dtimeInserted) {
		this.dtimeInserted = dtimeInserted;
	}

}
