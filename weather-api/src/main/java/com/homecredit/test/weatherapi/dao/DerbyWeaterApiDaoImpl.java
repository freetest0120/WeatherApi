package com.homecredit.test.weatherapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homecredit.test.weatherapi.model.WeatherLog;

@Repository
public class DerbyWeaterApiDaoImpl implements WeatherApiDao {

	@Autowired
	private DataSource datasource;

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public int insertWeatherLog(String responseId, String location, String actualWeather, String temperature) {

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"insert into WEATHERLOG (responseId,location,actualWeather,temperature) values (?,?,?,?)");
			stmt.setString(1, responseId);
			stmt.setString(2, location);
			stmt.setString(3, actualWeather);
			stmt.setString(4, temperature);
			stmt.executeUpdate();

			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return 0;
	}

	public List<WeatherLog> getRecentWeatherLog(int count) {
		List<WeatherLog> list = new ArrayList<WeatherLog>();
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM WEATHERLOG ORDER BY dtimeInserted DESC FETCH NEXT " + count + " ROWS ONLY ");
			while (rs.next()) {
				int id = rs.getInt("id");
				String responseId = rs.getString("responseId");
				String location = rs.getString("location");
				String actualWeather = rs.getString("actualWeather");
				String temperature = rs.getString("temperature");
				String dtimeInserted = rs.getString("dtimeInserted");

				list.add(new WeatherLog(id, responseId, location, actualWeather, temperature, dtimeInserted));

				System.out.printf(
						"ID[%s], responseID[%s], location[%s], actualWeather[%s], temperature[%s], timestampp[%s]",
						rs.getInt("id"), rs.getString("responseId"), rs.getString("location"),
						rs.getString("actualWeather"), rs.getString("temperature"), rs.getString("dtimeInserted"));

				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	private Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
