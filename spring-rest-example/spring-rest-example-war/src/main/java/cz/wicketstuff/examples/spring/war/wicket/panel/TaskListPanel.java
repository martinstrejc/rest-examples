package cz.wicketstuff.examples.spring.war.wicket.panel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;


import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.Task.Sort;
import cz.wicketstuff.examples.spring.core.domain.TaskExt;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.service.TaskPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxButton;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxLink;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaColumn;import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;


/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private TaskPersistenceService persistence;

	public TaskListPanel(String id, final IModel<TaskGroup> model) {
		super(id, model);
		
		List<IColumn<Task, Sort>> columns = new LinkedList<>();
		columns.add(new PropertyColumn<Task, Sort>(Model.of("ID"), Sort.ID, "id"));
		columns.add(new PropertyColumn<Task, Sort>(Model.of("Name"), Sort.NAME, "name"));
		columns.add(new PropertyColumn<Task, Sort>(Model.of("Priority"), Sort.PRIORITY, "priority"));
		columns.add(new PropertyColumn<Task, Sort>(Model.of("Status"), Sort.STATUS, "status"));
		columns.add(new LambdaColumn<Task, Sort>(Model.of("Action"), (populating) -> {
			Fragment fragment = new Fragment(populating.componentId, "actionFragment", TaskListPanel.this);
			fragment.add(new LambdaAjaxLink<Void>("delete", (target, linkModel) -> {
				persistence.delete(populating.rowModel.getObject());
				// taskService.deleteTask(populating.rowModel.getObject());
				setResponsePage(getWebPage());					
			}));
			populating.cellItem.add(fragment);			
		}));
		ISortableDataProvider<Task, Sort> dataProvider = new SortableDataProvider<Task, Sort>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<? extends Task> iterator(long first, long count) {
				SortParam<Sort> sorting = getSort();
				return persistence.getAll(model.getObject(), sorting.getProperty()).iterator();
				// return taskService.getTasks(model.getObject(), sorting.getProperty(), sorting.isAscending()).iterator();
			}

			@Override
			public IModel<Task> model(Task object) {
				return Model.of(object);
			}

			@Override
			public long size() {
				SortParam<Sort> sorting = getSort();
				return persistence.getAll(model.getObject(), sorting.getProperty()).size();
				// return taskService.getTasksCount(model.getObject());
			}
			
		};
		dataProvider.getSortState().setPropertySortOrder(Sort.ID, SortOrder.ASCENDING);
		DefaultDataTable<Task, Sort> table = new DefaultDataTable<>("table", columns, dataProvider, Integer.MAX_VALUE);
		add(table);
		
		final IModel<TaskExt> taskModel = new CompoundPropertyModel<>(new TaskExt());
		Form<TaskExt> form = new Form<>("form", taskModel);
		form.add(new TextField<String>("name"));
		form.add(new TextField<Integer>("priority"));
		form.add(new LambdaAjaxButton("submit", (target, buttonForm) -> {
			taskModel.getObject().setTaskGroup(model.getObject());
			persistence.save(taskModel.getObject());
			taskModel.setObject(new TaskExt());
			setResponsePage(new HomePage(model));
		}, null));
		add(form);
				
	}

}
