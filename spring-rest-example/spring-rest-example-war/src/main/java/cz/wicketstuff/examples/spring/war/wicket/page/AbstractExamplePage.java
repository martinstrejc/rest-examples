package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Martin Strejc (strma17)
 *
 */
public abstract class AbstractExamplePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public AbstractExamplePage() {
		super();
	}

	public AbstractExamplePage(IModel<?> model) {
		super(model);
	}

	public AbstractExamplePage(PageParameters parameters) {
		super(parameters);
	}

}
