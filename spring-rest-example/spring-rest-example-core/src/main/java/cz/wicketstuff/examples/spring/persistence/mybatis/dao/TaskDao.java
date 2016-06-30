package cz.wicketstuff.examples.spring.persistence.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import cz.wicketstuff.examples.spring.core.domain.Task;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Repository
public interface TaskDao {
	
	@Select("SELECT t.* FROM task t WHERE t.task_group_id = #{taskGroupId} ORDER BY t.created")
	@ResultMap("task")
	List<Task> selectAll(long taskGroupId);

	@Select("SELECT tg.*, t.id as t_id, t.name as t_name, t.created as t_created, t.uuid as t_uuid FROM task_group tg LEFT JOIN task t ON (t.task_group_id = tg.id) ORDER BY tg.created")
	@ResultMap("taskExt")
	List<Task> selectAllExt();

	@Insert("INSERT INTO task (name, created, uuid, status, task_group_id) VALUES (#{name}, #{created}, #{uuidString}, #{status}, #{taskGroup.id})")
	@SelectKey(
			before = false, 
			keyColumn = "id", 
			keyProperty = "id", 
			resultType = Long.class, 
			statement = "SELECT LAST_INSERT_ID()", 
			statementType = StatementType.STATEMENT)
	long insert(Task task);
	
	@Update("UPDATE task_group SET name = #{name}, status = #{status} WHERE id = #{id}")
	long update(Task task);
	
	@Delete("DELETE FROM task WHERE id = #{taskId}")
	long delete(long id);

	@Select("SELECT * FROM task WHERE id = #{id}")
	Task selectById(long id);

	@Select("SELECT t.*, tg.id as tg_id, tg.name as tg_name, tg.created as tg_created, tg.uuid as tg_uuid FROM task t LEFT JOIN task_group tg ON (tg.id = t.task_group_id) WHERE t.id = #{id}")
	@ResultMap("taskExt")
	Task selectByIdExt(long id);

	@Select("SELECT * FROM task_group WHERE uuid = #{uuidString}")
	Task selectByUuid(String uuidString);

	@Select("SELECT t.*, tg.id as tg_id, tg.name as tg_name, tg.created as tg_created, tg.uuid as tg_uuid FROM task t LEFT JOIN task_group tg ON (tg.id = t.task_group_id) WHERE t.id = #{uuidString}")
	@ResultMap("taskExt")
	Task selectByUuidExt(String uuidString);

}
