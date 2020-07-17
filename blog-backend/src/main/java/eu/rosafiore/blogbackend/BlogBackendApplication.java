package eu.rosafiore.blogbackend;

import eu.rosafiore.blogbackend.config.Config;
import eu.rosafiore.blogbackend.exception.IncompleteConfigException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class BlogBackendApplication {

	static class EnvironmentPreperation implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
		@Override
		public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
			Config.getConfig();
			if (Config.USERNAME.isEmpty() || Config.PASSWORD.isEmpty()) throw new IncompleteConfigException();
		}
	}

	public static void main(String[] args) {
		SpringApplication blogBackend  = new SpringApplication();
		blogBackend.addListeners(new BlogBackendApplication.EnvironmentPreperation());
		blogBackend.setSources(new HashSet(Arrays.asList(BlogBackendApplication.class)));
		ConfigurableApplicationContext context = blogBackend.run(args);
		System.out.println("DbUsername: " + Config.USERNAME + " ; DbPassword: " + Config.PASSWORD);
	}
}
