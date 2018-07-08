package com.homecredit.test.weatherapi;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "com.homecredit.test.weatherapi" })
public class WeatherApi {

	public static final String DB_URL = "c:\\test\\WEATHERAPI\\;create=true";

	@Bean
	public DataSource dataSource() throws Exception {
		EmbeddedDataSource ds = new EmbeddedDataSource();
		ds.setDatabaseName(DB_URL);
		return ds;
	}

	public static void main(String[] args) {
		if (initDB()) {
			SpringApplication.run(WeatherApi.class, args);
		} else {
			System.out.println("DATABASE INITIALIZATION FAILED");
		}
	}

	public static boolean initDB() {
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		Statement statement = null;

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection("jdbc:derby:" + DB_URL);
			dbmd = conn.getMetaData();
			rs = dbmd.getTables(null, null, "WEATHERLOG", null);
			statement = null;
			if (!rs.next()) {

				statement = conn.createStatement();

				String createTableQuery = "CREATE TABLE WEATHERLOG ("
						+ "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
						+ "responseId VARCHAR(45) NOT NULL," + "location VARCHAR(45) NOT NULL,"
						+ "actualWeather VARCHAR(45) NOT NULL," + "temperature VARCHAR(45) NOT NULL,"
						+ "dtimeInserted timestamp default current_timestamp)";

				statement.executeUpdate(createTableQuery);

				System.out.println("TABLE CREATED");

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}