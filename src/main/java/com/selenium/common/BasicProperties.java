package com.selenium.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class BasicProperties {
	
	public Properties load(String fileName) {
		Properties r = new Properties();
		InputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			r.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

}
