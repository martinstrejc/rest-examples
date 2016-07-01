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

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup.Sort;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskGroupDataProvider extends SortableDataProvider<TaskGroup, Sort> {
	
	private static final long serialVersionUID = 1L;

	private final TaskGroupPersistenceService persistence;
	
	public TaskGroupDataProvider(TaskGroupPersistenceService persistence) {
		super();
		this.persistence = persistence;
	}

	@Override
	public Iterator<? extends TaskGroup> iterator(long first, long count) {
		SortParam<Sort> sorting = getSort();
		return persistence.getAll(sorting.getProperty(), sorting.isAscending()).iterator();
	}

	@Override
	public IModel<TaskGroup> model(TaskGroup object) {
		return Model.of(object);
	}

	@Override
	public long size() {
		return persistence.countAll();
	}
	
	public static TaskGroupDataProvider createDetault(TaskGroupPersistenceService persistence) {
		TaskGroupDataProvider provider = new TaskGroupDataProvider(persistence); 
		provider.getSortState().setPropertySortOrder(Sort.ID, SortOrder.ASCENDING);
		return provider;
	}

}
