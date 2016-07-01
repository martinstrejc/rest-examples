/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.wicketstuff.examples.spring.war.wicket.data;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.Task.Sort;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.service.TaskPersistenceService;
import cz.wicketstuff.examples.spring.war.wicket.extension.LambdaSortableDataProvider;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskDataProvider extends LambdaSortableDataProvider<Task, Sort> {

	private static final long serialVersionUID = 1L;

	public TaskDataProvider(final TaskPersistenceService persistence, final IModel<TaskGroup> taskGroupModel) {
		super((first, count, provider) -> {
				SortParam<Sort> sorting = provider.getSort();
				return persistence.getAll(taskGroupModel.getObject(), sorting.getProperty()).iterator();
			},
			() -> { 
				return persistence.countAll(taskGroupModel.getObject()); 
			}
		);
	}
	
	public static TaskDataProvider createDefault(TaskPersistenceService persistence, IModel<TaskGroup> taskGroupModel) {		
		TaskDataProvider provider = new TaskDataProvider(persistence, taskGroupModel);
		provider.getSortState().setPropertySortOrder(Sort.ID, SortOrder.ASCENDING);
		return provider;
	}

}
