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
package cz.wicketstuff.examples.spring.persistence.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.wicketstuff.examples.spring.core.domain.AbstractDomainObject;
import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.domain.Task.Sort;
import cz.wicketstuff.examples.spring.core.domain.Task.Status;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.persistence.mybatis.dao.TaskDao;
import cz.wicketstuff.examples.spring.persistence.service.TaskPersistenceService;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Service("taskPersistenceService")
public class TaskPersistenceServiceImpl implements TaskPersistenceService {

	private final TaskDao dao;

	@Autowired
	public TaskPersistenceServiceImpl(TaskDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean save(Task task) {
		if (task.getId() == null) {
			task.setCreated(new Date());
			task.setUuid(UUID.randomUUID());
			task.setStatus(Status.NEW);
			task.useDefaults();
			long id = dao.insert(task);
			task.setId(id);
			return true;
		} else {
			return dao.update(task) > 0;
		}
		
	}

	@Override
	public List<Task> getAll(TaskGroup taskGroup, Sort sort, boolean ascending) {
		return AbstractDomainObject.exists(taskGroup) ? dao.selectAll(taskGroup.getId(), TaskDao.sqlSort(sort, ascending)) : null;
	}

	@Override
	public Task get(long id) {
		return dao.selectByIdExt(id);
	}
	
	@Override
	public boolean delete(Task task) {
		return dao.delete(task.getId()) > 0;
	}

	@Override
	public long countAll(TaskGroup taskGroup) {
		return AbstractDomainObject.exists(taskGroup) ? dao.countAll(taskGroup.getId()) : 0;
	}
	
}
