package cz.wicketstuff.examples.spring.war.wicket.panel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
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
import cz.wicketstuff.examples.spring.core.service.TaskService;
import cz.wicketstuff.examples.spring.persistence.mybatis.dao.TaskGroupDao;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxButton;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaAjaxLink;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaColumn;
import cz.wicketstuff.examples.spring.war.wicket.page.HomePage;


/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskGroupListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(TaskGroupListPanel.class);
	
	@SpringBean
	private TaskService taskService;
	
	@SpringBean
	private TaskGroupPersistenceService persistence;

	public TaskGroupListPanel(String id, IModel<?> model) {
		super(id, model);
		
		List<IColumn<TaskGroup, Sort>> columns = new LinkedList<>();
		columns.add(new PropertyColumn<TaskGroup, Sort>(Model.of("Created"), Sort.CREATED, "created"));
		columns.add(new LambdaColumn<TaskGroup, Sort>(Model.of("Name"), Sort.NAME, (populating) -> {
			Fragment fragment = new Fragment(populating.componentId, "nameFragment", TaskGroupListPanel.this);
			Component link = new LambdaAjaxLink<Void>("link", (target, linkModel) -> {
				// navigation
				setResponsePage(new HomePage(Model.of(populating.rowModel)));
			}).add(new Label("name", populating.rowModel.getObject().getName()));
			fragment.add(link);
			populating.cellItem.add(fragment);						
		}));
		columns.add(new LambdaColumn<TaskGroup, Sort>(Model.of("Action"), (populating) -> {
			Fragment fragment = new Fragment(populating.componentId, "actionFragment", TaskGroupListPanel.this);
			fragment.add(new LambdaAjaxLink<Void>("delete", (target, linkModel) -> {
				persistence.delete(populating.rowModel.getObject());
				// taskService.deleteTaskGroup(populating.rowModel.getObject());
				setResponsePage(getWebPage());					
			}));
			populating.cellItem.add(fragment);			
		}));
		ISortableDataProvider<TaskGroup, Sort> dataProvider = new SortableDataProvider<TaskGroup, Sort>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<? extends TaskGroup> iterator(long first, long count) {
				SortParam<Sort> sorting = getSort();
				// return taskGroupDao.selectAll().iterator();
				return persistence.getAll(sorting.getProperty()).iterator();
				// return taskService.getTaskGroups(sorting.getProperty(), sorting.isAscending()).iterator();
			}

			@Override
			public IModel<TaskGroup> model(TaskGroup object) {
				return Model.of(object);
			}

			@Override
			public long size() {
				return persistence.getAll(getSort().getProperty()).size();
				// return persistence.getAll(getSort().getProperty()).;
				// return taskService.getTaskGroupsCount();
			}
			
		};
		dataProvider.getSortState().setPropertySortOrder(Sort.ID, SortOrder.ASCENDING);
		DefaultDataTable<TaskGroup, Sort> table = new DefaultDataTable<>("table", columns, dataProvider, Integer.MAX_VALUE);
		add(table);
		
		final IModel<TaskGroup> taskModel = new CompoundPropertyModel<>(new TaskGroup());
		Form<TaskGroup> form = new Form<>("form", taskModel);
		form.add(new TextField<String>("name"));
		form.add(new LambdaAjaxButton("submit", (target, buttonForm) -> {
			persistence.save(taskModel.getObject());
			// taskGroupDao.insert(taskModel.getObject());
			// log.debug("taskGroup.id = {}", taskModel.getObject().getId());
			/// taskService.createTaskGroup(taskModel.getObject());
			taskModel.setObject(new TaskGroup());
			setResponsePage(getWebPage());
		}, null));
		add(form);
				
	}

}
