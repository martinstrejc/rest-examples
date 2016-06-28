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
package cz.wicketstuff.examples.spring.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.Task.Sort;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class MemoryTaskService implements TaskService {
	
	private final List<Task> tasks = new LinkedList<>();

	public Task createTask(Task task) {
		task.setId(new Date().getTime());
		tasks.add(task);
		return task;
	}

	public void saveTask(Task task) {
		// do nothing
	}

	public void deleteTask(Task task) {
		tasks.remove(task);
		
	}

	public void setTaskPriority() {
		// do nothing
	}

	@Override
	public int getTasksCount() {
		return tasks.size();
	}

	@Override
	public List<Task> getTasks(Sort sort, boolean ascending) {
		List<Task> list = new ArrayList<>(tasks);
		Collections.sort(list, Sort.ID == sort ? new IdComparator(ascending) : new NameComparator(ascending));
		return list;
	}
	
	public static class IdComparator implements Comparator<Task> {
		
		private final boolean ascending;

		public IdComparator(boolean ascending) {
			super();
			this.ascending = ascending;
		}

		@Override
		public int compare(Task task1, Task task2) {
			return task1.getId().compareTo(task2.getId()) * (ascending ? 1 : -1);
		}
		
	}
		
	public static class NameComparator implements Comparator<Task> {

		private final boolean ascending;

		public NameComparator(boolean ascending) {
			super();
			this.ascending = ascending;
		}


		@Override
		public int compare(Task task1, Task task2) {
			return task1.getName().compareTo(task2.getName()) * (ascending ? 1 : -1);
		}
		
	}


}
