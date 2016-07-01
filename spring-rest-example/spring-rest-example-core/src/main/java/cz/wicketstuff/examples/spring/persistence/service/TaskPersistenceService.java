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
package cz.wicketstuff.examples.spring.persistence.service;

import java.util.List;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup;

/**
 * @author Martin Strejc (strma17)
 *
 */
public interface TaskPersistenceService {
	
	boolean save(Task task);
	
	List<Task> getAll(TaskGroup taskGroup, Task.Sort sort);

	Task get(long id);
	
	boolean delete(Task task);
	
	long countAll(TaskGroup taskGroup);

}
