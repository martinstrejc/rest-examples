/**
 * 
 */
package cz.wicketstuff.examples.spring.core.service;

import cz.wicketstuff.examples.spring.core.domain.Task;

/**
 * @author Martin Strejc (strma17)
 *
 */
public interface TaskServiceFacade {

	Task createTask(String name);
	
	Task createTask(String name, Integer priority);
	
	void deleteTask(Task task);
	
	Task changeTaskName(Task task, String name);
	
	Task changeTaskPriority(Task task, Integer priority);
	
	Task changeTaskStatus(Task task, Status status);
	
}
