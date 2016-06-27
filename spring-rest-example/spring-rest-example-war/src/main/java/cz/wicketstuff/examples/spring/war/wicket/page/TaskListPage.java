package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskListPage extends AbstractExamplePage {

	private static final long serialVersionUID = 1L;

	public TaskListPage() {
		super();
	}

	public TaskListPage(IModel<?> model) {
		super(model);
	}

	public TaskListPage(PageParameters parameters) {
		super(parameters);
	}

}
