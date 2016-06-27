package cz.wicketstuff.examples.spring.war.wicket.panel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cz.wicketstuff.examples.spring.core.service.Task;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private final List<Task> list = new ArrayList<>();

	public TaskListPanel(String id, IModel<?> model) {
		super(id, model);
		
		List<IColumn<Task, String>> columns = new LinkedList<IColumn<Task,String>>();
		columns.add(new PropertyColumn<Task, String>(Model.of("Name"), "name", "name"));
		columns.add(new PropertyColumn<Task, String>(Model.of("Priority"), "priority", "priority"));
		columns.add(new PropertyColumn<Task, String>(Model.of("Status"), "status", "status"));
		ISortableDataProvider<Task, String> dataProvider = new SortableDataProvider<Task, String>() {

			private static final long serialVersionUID = 1L;

			public Iterator<? extends Task> iterator(long first, long count) {
				return list.iterator();
			}

			public IModel<Task> model(Task object) {
				return Model.of(object);
			}

			public long size() {
				return list.size();
			}
			
		};
		DefaultDataTable<Task, String> table = new DefaultDataTable<Task, String>("table", columns, dataProvider, Integer.MAX_VALUE);
		add(table);
		
		final IModel<Task> taskModel = new CompoundPropertyModel<Task>(new Task());
		Form<Task> form = new Form<Task>("form", taskModel);
		form.add(new TextField<String>("name"));
		form.add(new TextField<Integer>("priority"));
		form.add(new AjaxButton("submit") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				list.add(taskModel.getObject());
				taskModel.setObject(new Task());
				setResponsePage(getWebPage());
			}
			
		});
		add(form);
				
	}

}
