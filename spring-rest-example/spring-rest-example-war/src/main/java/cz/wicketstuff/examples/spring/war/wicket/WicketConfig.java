package cz.wicketstuff.examples.spring.war.wicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Configuration
public class WicketConfig {

	@Autowired
	@Bean
	public ExampleApplication exampleApplication(ApplicationContext context) {
		return new ExampleApplication(context);
	}
	
}
