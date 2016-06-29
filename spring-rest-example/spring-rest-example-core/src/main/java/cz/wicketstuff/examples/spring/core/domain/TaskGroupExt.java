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
@Alias("TaskGroupExt")
public class TaskGroupExt extends TaskGroup {

	private static final long serialVersionUID = 1L;
	
	private List<Task> tasks;
	

	public TaskGroupExt() {
		super();
	}

	public TaskGroupExt(TaskGroup other) {
		super(other);
	}

	public TaskGroupExt(TaskGroupExt other) {
		super(other);
		this.tasks = other.tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
