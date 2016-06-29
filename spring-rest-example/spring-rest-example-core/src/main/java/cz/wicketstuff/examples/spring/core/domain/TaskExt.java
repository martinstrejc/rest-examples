package cz.wicketstuff.examples.spring.core.domain;

import org.apache.ibatis.type.Alias;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Alias("TaskExt")
public class TaskExt extends Task {

	private static final long serialVersionUID = 1L;
	
	private TaskGroup taskGroup;
	
	public TaskExt() {
		super();
	}

	public TaskExt(Task other) {
		super(other);
	}

	public TaskExt(TaskExt other) {
		super(other);
		this.taskGroup = other.taskGroup;
	}

	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}
	
}
