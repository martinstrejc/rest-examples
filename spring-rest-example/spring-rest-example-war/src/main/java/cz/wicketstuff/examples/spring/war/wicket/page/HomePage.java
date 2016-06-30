package cz.wicketstuff.examples.spring.war.wicket.page;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskGroupListPanel;
import cz.wicketstuff.examples.spring.war.wicket.panel.TaskListPanel;

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
		add(new TaskGroupListPanel("taskGroupListPanel", new Model<>()));
		add(new TaskListPanel("taskListPanel", model));
		
		WebMarkupContainer taskGroup = new WebMarkupContainer("taskGroup", CompoundPropertyModel.of(model)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(model.getObject() != null);
			}
		};
		taskGroup.add(new Label("id"));
		taskGroup.add(new Label("name"));
		taskGroup.add(new Label("created"));
		taskGroup.add(new Label("uuidString"));
		// , new PageParameters().add(TASK_GROUP_PARAM, model.getObject().getUuidString()
		
		PageParameters params = new PageParameters();
		if (model.getObject() != null) {
			params.add(TASK_GROUP_PARAM, model.getObject().getUuidString());			
		}
		BookmarkablePageLink<TaskGroup> link = new BookmarkablePageLink<>("link", HomePage.class, params);
		String url = getRequestCycle().getUrlRenderer().renderFullUrl(Url.parse(urlFor(HomePage.class, params)));
		link.add(new Label("text", url));
		taskGroup.add(link);
		add(taskGroup);
	}
	
	@Override
	protected IModel<?> initModel() {
		return new Model<>();
	}

}
