package cz.wicketstuff.examples.spring.core.domain;

import java.io.Serializable;
import java.util.Date;

import cz.wicketstuff.examples.spring.core.service.Status;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private Status status;
	
	private Integer priority;
	
	private Date created;

	public Task() {
		// default constructor
	}

	public Task(Long id, String name, Status status, Integer priority) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public enum Sort {
		
		ID,
		
		NAME,
		
		PRIORITY,
		
		STATUS,
		
		CREATED;
		
	}
	
}
