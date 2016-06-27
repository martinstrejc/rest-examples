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

	public Task(Integer id, String name, Status status, Integer priority) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.priority = priority;
	}
	
	
	
	
	
	

}
