package vn.com.arillance.cleanhome.config.app;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import vn.com.arillance.cleanhome.controller.AmazingWallpapersController;
import vn.com.arillance.cleanhome.controller.HackathonController;
import vn.com.arillance.cleanhome.exception.ExceptionMapper;

@Configuration
@ComponentScan("vn.com.arillance.cleanhome")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(HackathonController.class);
		register(ExceptionMapper.class);
		register(MultiPartFeature.class);

	}
}
