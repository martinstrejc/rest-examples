package cz.wicketstuff.examples.spring.war.wicket.panel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;







import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableChoiceLabel;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;







import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.Task.Sort;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.service.TaskPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.data.TaskDataProvider;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxButton;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxLink;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaColumn;
import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;


/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private TaskPersistenceService persistence;

	public TaskPanel(String id, final IModel<TaskGroup> model) {
		super(id, model);
		
		add(newTaskTable(model));
		add(newTaskAddForm(model));
				
	}
	
	protected DataTable<Task, Sort> newTaskTable(final IModel<TaskGroup> taskGroupModel) {
		List<IColumn<Task, Sort>> columns = new LinkedList<>();
		columns.add(new PropertyColumn<Task, Sort>(Model.of("ID"), Sort.ID, "id"));
//		columns.add(new PropertyColumn<Task, Sort>(Model.of("Name"), Sort.NAME, "name"));

		columns.add(new LambdaColumn<Task, Sort>(Model.of("Name"), Sort.NAME, (cellItem, componentId, rowModel) -> {
			
			cellItem.add(new AjaxEditableLabel<String>(componentId, new PropertyModel<String>(rowModel, "name")) {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					persistence.save(rowModel.getObject());
					super.onSubmit(target);
				}
				
				
			});
		}));

		
		// columns.add(new PropertyColumn<Task, Sort>(Model.of("Priority"), Sort.PRIORITY, "priority"));

		columns.add(new LambdaColumn<Task, Sort>(Model.of("Priority"), Sort.PRIORITY, (cellItem, componentId, rowModel) -> {
			
			cellItem.add(new AjaxEditableLabel<Integer>(componentId, new PropertyModel<Integer>(rowModel, "priority")) {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					persistence.save(rowModel.getObject());
					super.onSubmit(target);
				}
				
				
			});
		}));

		
		
		columns.add(new PropertyColumn<Task, Sort>(Model.of("Created"), Sort.CREATED, "created"));
		// columns.add(new PropertyColumn<Task, Sort>(Model.of("Status"), Sort.STATUS, "status"));
		
		columns.add(new LambdaColumn<Task, Sort>(Model.of("Status"), Sort.STATUS, (cellItem, componentId, rowModel) -> {
			
			
			
			cellItem.add(new AjaxEditableChoiceLabel<Task.Status>(componentId, new PropertyModel<Task.Status>(rowModel, "status"), Arrays.asList(Task.Status.values())) {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					persistence.save(rowModel.getObject());
					super.onSubmit(target);
				}
				
				
			});
		}));
		
		columns.add(new LambdaColumn<Task, Sort>(Model.of("Action"), (cellItem, componentId, rowModel) -> {
			Fragment fragment = new Fragment(componentId, "actionFragment", TaskPanel.this);
			fragment.add(new LambdaAjaxLink<Void>("delete", (ltarget, lmodel) -> {
				persistence.delete(rowModel.getObject());
				setResponsePage(getWebPage());					
			}));
			cellItem.add(fragment);			
		}));
		
		return new DefaultDataTable<>("table", columns, TaskDataProvider.createDefault(persistence, taskGroupModel), Integer.MAX_VALUE);		
	}
	
	protected Form<Task> newTaskAddForm(final IModel<TaskGroup> taskGroupModel) {
		final IModel<Task> taskModel = new CompoundPropertyModel<>(new Task());
		Form<Task> form = new Form<>("form", taskModel);
		form.add(new TextField<String>("name"));
		form.add(new TextField<Integer>("priority"));
		form.add(new LambdaAjaxButton("submit", (target, lform) -> {
			taskModel.getObject().setTaskGroup(taskGroupModel.getObject());
			persistence.save(taskModel.getObject());
			taskModel.setObject(new Task());
			setResponsePage(new HomePage(taskGroupModel));
		}, null));
		return form;
	}

}
