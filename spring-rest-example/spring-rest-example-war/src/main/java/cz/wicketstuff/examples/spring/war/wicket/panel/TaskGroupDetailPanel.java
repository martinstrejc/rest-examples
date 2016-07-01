package cz.wicketstuff.examples.spring.war.wicket.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskGroupDetailPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private final PageParameters params = new PageParameters();
	
	public TaskGroupDetailPanel(String id, IModel<TaskGroup> model) {
		super(id, CompoundPropertyModel.of(model));

		setOutputMarkupPlaceholderTag(true);

		add(new Label("id"));
		add(new Label("name"));
		add(new Label("created"));
		add(new Label("uuidString"));
		
		BookmarkablePageLink<TaskGroup> link = new BookmarkablePageLink<>("link", HomePage.class, params);
		String url = getRequestCycle().getUrlRenderer().renderFullUrl(Url.parse(urlFor(HomePage.class, params)));
		link.add(new Label("text", url));
		add(link);
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		TaskGroup taskGroup = (TaskGroup) getDefaultModelObject();
		setVisible(taskGroup != null);
		if (taskGroup != null) {
			params.set(HomePage.TASK_GROUP_PARAM, taskGroup.getUuidString());			
		}
	}

}
