package cz.wicketstuff.examples.spring.core.service;

import java.io.Serializable;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String name;

	private Status status;
	
	private Integer priority;

	public Task() {
		// default constructor
	}

	public Task(Integer id, String name, Status status, Integer priority) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.priority = priority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	

}
