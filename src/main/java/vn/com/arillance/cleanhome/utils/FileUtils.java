package vn.com.arillance.cleanhome.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	private static FileUtils singleton = new FileUtils();
	private static final String username = System.getProperty("user.name");
	private static File dir;

	public static FileUtils getInstances() {
		return singleton;
	}

	public static boolean cleanTempDir() throws IOException {
		dir = new File("C:/Users/" + username + "/AppData/Local/Temp");
		for (File temp : dir.listFiles()) {
			System.out.println(temp.getName());
			if (temp.getName().toLowerCase().contains("webdriver")) {
				try {
					org.apache.commons.io.FileUtils.deleteDirectory(temp);
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
		// org.apache.commons.io.FileUtils.cleanDirectory(dir);
		return true;
	}
	
	public static void writeToFile(String filePath, String content, boolean append) {
		try {

			File file = new File(filePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), append);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
			bw.close();

//			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
