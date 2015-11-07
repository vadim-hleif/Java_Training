package se05.task2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	private Properties properties = new Properties();

	public void readFile(String fileName) {
		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(new File(".\\src\\main\\resources\\" + fileName + ".properties")))) {

			properties.load(bufferedInputStream);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}

	public String getValue(String key) {

		try {
			String value = properties.getProperty(key);
			if (value == null) {
				throw new IllegalArgumentException(key + " - Key not found");
			}
			return value;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}
}
