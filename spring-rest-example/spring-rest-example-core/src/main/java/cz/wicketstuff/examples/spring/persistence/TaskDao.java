package cz.wicketstuff.examples.spring.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.service.Status;

/**
 * @author Martin Strejc (strma17)
 *
 */
public interface TaskDao {
	
	@Select("SELECT * FROM task")
	public List<Task> selectTasks();
	
	public Task createTask(String name);
	
	public Task createTask(String name, Integer priority);
	
	@Delete("DELETE FROM taks WHERE id = #{taskId}")
	public void deleteTask(Integer taskId);
	
	@Update("UPDATE task SET name = #{name} WHERE id = #{taskId}")
	public Task changeTaskName(Integer taskId, String name);
	
	@Update("UPDATE task SET status = #{name} WHERE id = #{taskId}")
	public Task changeTaskPriority(Integer taskId, Integer priority);
	
	@Update("UPDATE task SET name = #{name} WHERE id = #{taskId}")
	public Task changeTaskStatus(Integer taskId, Status status);
	

}
