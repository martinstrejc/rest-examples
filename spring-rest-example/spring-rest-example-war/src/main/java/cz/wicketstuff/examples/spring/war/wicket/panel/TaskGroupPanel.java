package cz.wicketstuff.examples.spring.war.wicket.panel;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup.Sort;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.data.TaskGroupDataProvider;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxButton;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxLink;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaColumn;
import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;


/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskGroupPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(TaskGroupPanel.class);
	
	@SpringBean
	private TaskGroupPersistenceService persistence;

	public TaskGroupPanel(String id, IModel<?> model) {
		super(id, model);
		
		add(newTaskGroupTable());
		add(newTaskGroupAddForm());
				
	}
	
	protected DataTable<TaskGroup, Sort> newTaskGroupTable() {
		List<IColumn<TaskGroup, Sort>> columns = new LinkedList<>();
		columns.add(new PropertyColumn<TaskGroup, Sort>(Model.of("Created"), Sort.CREATED, "created"));
		columns.add(new LambdaColumn<TaskGroup, Sort>(Model.of("Name"), Sort.NAME, (cellItem, componentId, rowModel) -> {
			Fragment fragment = new Fragment(componentId, "nameFragment", TaskGroupPanel.this);
			Component link = new LambdaAjaxLink<Void>("link", (ltarget, lmodel) -> {
				// navigation
				setResponsePage(new HomePage(rowModel));
			}).add(new Label("name", rowModel.getObject().getName()));
			fragment.add(link);
			cellItem.add(fragment);						
		}));
		columns.add(new LambdaColumn<TaskGroup, Sort>(Model.of("Action"), (cellItem, componentId, rowModel) -> {
			Fragment fragment = new Fragment(componentId, "actionFragment", TaskGroupPanel.this);
			fragment.add(new LambdaAjaxLink<Void>("delete", (ltarget, lmodel) -> {
				persistence.delete(rowModel.getObject());
				setResponsePage(getWebPage());					
			}));
			cellItem.add(fragment);			
		}));
		return new DefaultDataTable<>("table", columns, TaskGroupDataProvider.createDetault(persistence), Integer.MAX_VALUE);
	}
	
	protected Form<TaskGroup> newTaskGroupAddForm() {
		final IModel<TaskGroup> taskModel = new CompoundPropertyModel<>(new TaskGroup());
		Form<TaskGroup> form = new Form<>("form", taskModel);
		form.add(new TextField<String>("name"));
		form.add(new LambdaAjaxButton("submit", (target, lform) -> {
			persistence.save(taskModel.getObject());
			setResponsePage(new HomePage(Model.of(taskModel.getObject())));
		}, null));
		return form;
	}

}
