package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskGroupListPanel;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskListPanel;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class HomePage extends AbstractExamplePage {

	private static final long serialVersionUID = 1L;
	
	public static final String TASK_GROUP_PARAM = "taskGroup";

	public HomePage() {
		super();
	}

	public HomePage(IModel<TaskGroup> model) {
		super(model);
	}

	public HomePage(PageParameters parameters) {
		super(parameters);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new TaskGroupListPanel("taskGroupListPanel", new Model<>()));
		add(new TaskListPanel("taskListPanel", (IModel<TaskGroup>)getDefaultModel()));
	}

}
