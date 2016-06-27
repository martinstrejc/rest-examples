package cz.wicketstuff.examples.spring.core.service;

/**
 * @author Martin Strejc (strma17)
 *
 */
public interface TaskService {

	Task createTask(Task task);
	
	void saveTask(Task task);
	
	void deleteTask(Task task);
	
	void setTaskPriority();
	
	
}
