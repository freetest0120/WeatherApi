package com.homecredit.test.weatherapi.model;

public enum Locations {
	London(2643743), Prague(3067696), San_Francisco(3669857);

	private int id;

	private Locations(int code) {
		this.id = code;
	}

	public static Locations valueof(int id) {
		for (Locations loc : Locations.values()) {
			if (loc.id == id)
				return loc;
		}
		return null;
	}

	public static String getAllId() {
		String ids = "";
		for (Locations loc : Locations.values()) {
			ids += loc.getId() + ",";
		}
		return ids.replaceAll(",$", "");
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return this.name().replaceAll("_", " ");
	}

}