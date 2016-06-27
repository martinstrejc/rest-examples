package cz.wicketstuff.examples.spring.war.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class ExampleApplication extends WebApplication {

	private final ApplicationContext context;
	
	public ExampleApplication(ApplicationContext context) {
		super();
		this.context = context;
	}

	@Override
	protected void init() {
		super.init();
		initListeners();
		mountPages();
		
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
	
	protected SpringComponentInjector newSpringComponentInjector() {
		return new SpringComponentInjector(this,
				context);
	}
	
	private void initListeners() {
		SpringComponentInjector injector = newSpringComponentInjector();
		getComponentInstantiationListeners().add(injector);
		getBehaviorInstantiationListeners().add(injector);
		
	}
	
	private void mountPages() {
		mountPage("/home", HomePage.class);
	} 

}
