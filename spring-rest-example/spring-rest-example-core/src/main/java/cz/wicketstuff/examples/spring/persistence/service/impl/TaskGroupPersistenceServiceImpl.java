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

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;
import cz.wicketstuff.examples.spring.core.domain.TaskGroup.Sort;
import cz.wicketstuff.examples.spring.persistence.mybatis.dao.TaskGroupDao;
import cz.wicketstuff.examples.spring.persistence.service.TaskGroupPersistenceService;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Service("taskGroupPersistenceService")
public class TaskGroupPersistenceServiceImpl implements TaskGroupPersistenceService {

	private final TaskGroupDao dao;
	
	@Autowired
	public TaskGroupPersistenceServiceImpl(TaskGroupDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean save(TaskGroup taskGroup) {
		if (taskGroup.getId() == null) {
			taskGroup.setCreated(new Date());
			taskGroup.setUuid(UUID.randomUUID());
			long id = dao.insert(taskGroup);
			taskGroup.setId(id);
			return true;
		} else {
			return dao.update(taskGroup) > 0;
		}
		
	}

	@Override
	public List<TaskGroup> getAll(Sort sort) {
		return dao.selectAll();
	}

	@Override
	public TaskGroup get(long id) {
		return dao.selectById(id);
	}

	@Override
	public TaskGroup getExt(long id) {
		return dao.selectByIdExt(id);
	}

	@Override
	public boolean delete(TaskGroup taskGroup) {
		return dao.delete(taskGroup.getId()) > 0;
	}
	
}
