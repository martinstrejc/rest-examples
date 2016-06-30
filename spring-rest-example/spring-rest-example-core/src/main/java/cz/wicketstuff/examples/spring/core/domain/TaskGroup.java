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
package cz.wicketstuff.examples.spring.core.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Alias("TaskGroup")
public class TaskGroup extends AbstractDomainObject {

	private static final long serialVersionUID = 1L;
	
	private Long tasksCount;
	
	private List<Task> tasks;
	
	public TaskGroup() {
		super();
	}

	public TaskGroup(TaskGroup other) {
		super(other);
		this.tasks = other.tasks;
		this.tasksCount = other.tasksCount;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Long getTasksCount() {
		return tasks == null ? tasksCount : tasks.size();
	}

	public void setTasksCount(Long tasksCount) {
		this.tasksCount = tasksCount;
	}

	public enum Sort {
		
		ID,
		
		NAME,
		
		CREATED;
		
	}

}
