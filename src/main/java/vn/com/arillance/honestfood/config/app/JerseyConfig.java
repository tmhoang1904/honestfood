package vn.com.arillance.honestfood.config.app;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import vn.com.arillance.honestfood.controller.AmazingWallpapersController;
import vn.com.arillance.honestfood.controller.HackathonController;
import vn.com.arillance.honestfood.exception.ExceptionMapper;

@Configuration
@ComponentScan("vn.com.arillance.honestfood")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(HackathonController.class);
		register(ExceptionMapper.class);
		register(MultiPartFeature.class);

	}
}
