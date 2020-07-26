package eu.rosafiore.blogbackend;

import eu.rosafiore.blogbackend.config.Config;
import eu.rosafiore.blogbackend.exception.IncompleteConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableAutoConfiguration (exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class BlogBackendApplication {

	static class EnvironmentPreperation implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
		@Override
		public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
			Config.getConfig();
			if (Config.USERNAME.isEmpty() || Config.PASSWORD == null || Config.PASSWORD.length == 0) throw new IncompleteConfigException();
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
