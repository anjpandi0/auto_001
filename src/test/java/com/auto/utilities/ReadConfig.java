package com.auto.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadConfig {

	String configPath = "";
	Properties prop = null;
	private String trackName;

	// private final Logger log = Logger.getLogger(GenericAdapter.class);
	final Logger log = Logger.getLogger("EXCEPTION");

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

	public Properties getConfigProperties() throws Exception {
		String configPath = "";
		trackName = prop.getProperty("trackname");
		String jobName = null;
		String workSpace = null;

		if (trackName != null)
			this.trackName = trackName;
		else {
			jobName = System.getenv("JOB_NAME");
			if (jobName != null) {
				String[] jobname = jobName.split("_");
				this.trackName = jobname[0];
			}
		}
		try {
			jobName = System.getenv("JOB_NAME");
			workSpace = System.getenv("WORKSPACE");

			configPath = "./Configurations/config.properties";

		} catch (Exception e) {
			configPath = System.getProperty("user.dir")+"/Configurations/config.properties";
			log.debug("Local configPath in catch" + configPath);
		}
		return prop;
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

	public String getFirefoxPath() {
		String ffpath = prop.getProperty("ffpath");

		return ffpath;
	}
	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
}
