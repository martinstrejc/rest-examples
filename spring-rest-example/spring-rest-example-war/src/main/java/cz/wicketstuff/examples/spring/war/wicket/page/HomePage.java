package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class HomePage extends AbstractExamplePage {

	private static final long serialVersionUID = 1L;

	public HomePage() {
		super();
	}

	public HomePage(IModel<?> model) {
		super(model);
	}

	public HomePage(PageParameters parameters) {
		super(parameters);
	}

}
