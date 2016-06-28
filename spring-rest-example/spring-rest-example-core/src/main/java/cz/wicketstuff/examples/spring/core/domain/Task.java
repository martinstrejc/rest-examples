package cz.wicketstuff.examples.spring.core.domain;

import cz.wicketstuff.examples.spring.core.service.Status;

/**
 * @author Martin Strejc (strma17)
 *
 */
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
	
	public enum Sort {
		
		ID,
		
		NAME,
		
		PRIORITY,
		
		STATUS,
		
		CREATED;
		
	}
	
}
