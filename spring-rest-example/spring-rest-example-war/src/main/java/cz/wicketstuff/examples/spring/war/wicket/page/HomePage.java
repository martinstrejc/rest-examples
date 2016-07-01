package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskGroupDetailPanel;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskGroupPanel;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskPanel;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class HomePage extends AbstractExamplePage {

	private static final long serialVersionUID = 1L;
	
	public static final String TASK_GROUP_PARAM = "taskGroup";

	@SpringBean
	private TaskGroupPersistenceService persistence;

	public HomePage() {
		super();
	}

	public HomePage(IModel<TaskGroup> model) {
		super(model);
	}

	public HomePage(PageParameters parameters) {
		super(parameters);
		setDefaultModel(Model.of(persistence.getByUuid(parameters.get(TASK_GROUP_PARAM).toString())));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onInitialize() {
		super.onInitialize();
		IModel<TaskGroup> model = (IModel<TaskGroup>)getDefaultModel();
		add(new Label("groupName", PropertyModel.of(model, "name")));				
		add(new TaskGroupPanel("taskGroupPanel", model));
		add(new TaskGroupDetailPanel("taskGroupDetailPanel", model));
		add(new TaskPanel("taskPanel", model));
		
	}
	
	@Override
	protected IModel<?> initModel() {
		return new Model<>();
	}

}
