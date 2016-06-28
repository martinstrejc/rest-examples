/**
 * 
 */
package cz.wicketstuff.examples.spring.rest.controller;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.service.Status;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class TaskController {

	public Task createTask(String name) {
		return null;
	}
	
	public Task createTask(String name, Integer priority) {
		return null;
	}
	
	public void deleteTask(Integer taskId) {
		
	}
	
	public Task changeTaskName(Integer taskId, String name) {
		return null;
	}
	
	public Task changeTaskPriority(Integer taskId, Integer priority) {
		return null;
	}
	
	public Task changeTaskStatus(Integer taskId, Status status) {
		return null;
	}

}
