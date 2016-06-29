package cz.wicketstuff.examples.spring.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cz.wicketstuff.examples.spring.core.domain.Task;
import cz.wicketstuff.examples.spring.core.service.Status;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Repository
public interface TaskDao {
	
	@Select("SELECT * FROM task")
	List<Task> selectTasks();
	
	Task createTask(String name);
	
	Task createTask(String name, Integer priority);
	
	@Delete("DELETE FROM taks WHERE id = #{taskId}")
	void deleteTask(Integer taskId);
	
	@Update("UPDATE task SET name = #{name} WHERE id = #{taskId}")
	Task changeTaskName(Integer taskId, String name);
	
	@Update("UPDATE task SET status = #{name} WHERE id = #{taskId}")
	Task changeTaskPriority(Integer taskId, Integer priority);
	
	@Update("UPDATE task SET name = #{name} WHERE id = #{taskId}")
	Task changeTaskStatus(Integer taskId, Status status);
	

}
