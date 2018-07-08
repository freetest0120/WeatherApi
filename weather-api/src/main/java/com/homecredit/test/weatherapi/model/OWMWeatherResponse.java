package com.homecredit.test.weatherapi.model;

import java.util.List;

public class OWMWeatherResponse {

	private String cnt;
	private List<OWMWeather> list;

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public List<OWMWeather> getList() {
		return list;
	}

	public void setList(List<OWMWeather> list) {
		this.list = list;
	}

}
