package com.auto.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {

		File file = new File("./Configurations/config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);

			prop = new Properties();

			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());

		}
	}

	public String getAppURL() {
		String url = prop.getProperty("baseURL");

		return url;

	}

	public String getUserName() {
		String user = prop.getProperty("userName");

		return user;
	}

	public String getPassword() {
		String pass = prop.getProperty("password");

		return pass;
	}

	public String getChromePath() {
		String chpath = prop.getProperty("chromepath");

		return chpath;
	}
}
