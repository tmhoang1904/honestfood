package vn.com.arillance.cleanhome.dao;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	private static SqlSessionFactory factory;

	private MyBatisUtils() {
	}

	static {
		Properties prop = new Properties();
		String enviroment = "";
		InputStream inputStream = MyBatisUtils.class.getClassLoader()
				.getResourceAsStream("application.properties");

		try {
			prop.load(inputStream);
			enviroment = prop.getProperty("app.environment");
			Reader reader = null;
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(reader,enviroment);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}