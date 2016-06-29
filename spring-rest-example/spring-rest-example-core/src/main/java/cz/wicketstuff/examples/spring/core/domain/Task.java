package cz.wicketstuff.examples.spring.core.domain;

import org.apache.ibatis.type.Alias;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Alias("Task")
public class Task extends AbstractDomainObject {

	private static final long serialVersionUID = 1L;
	
	private Status status;
	
	private Integer priority;
	
	
	public Task() {
		super();
	}

	public Task(Task other) {
		super(other);
		this.status = other.status;
		this.priority = other.priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	/**
	 * @author Martin Strejc (strma17)
	 *
	 */
	@Alias("TaskStatus")
	public enum Status {

		NEW,
		
		IN_PROGRESS,
		
		DONE;
		
	}
	
	public enum Sort {
		
		ID,
		
		NAME,
		
		PRIORITY,
		
		STATUS,
		
		CREATED;
		
	}
	
}
