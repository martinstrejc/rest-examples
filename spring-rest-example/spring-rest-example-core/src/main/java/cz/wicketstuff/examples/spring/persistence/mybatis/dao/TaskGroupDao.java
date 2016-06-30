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

import cz.wicketstuff.examples.spring.core.domain.TaskGroup;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Repository
public interface TaskGroupDao {
	
	@Select("SELECT tg.* FROM task_group tg ORDER BY tg.created")
	@ResultMap("taskGroup")
	List<TaskGroup> selectAll();

	@Select("SELECT tg.*, t.id as t_id, t.name as t_name, t.created as t_created, t.uuid as t_uuid FROM task_group tg LEFT JOIN task t ON (t.task_group_id = tg.id) ORDER BY tg.created")
	@ResultMap("taskGroupExt")
	List<TaskGroup> selectAllExt();

	@Insert("INSERT INTO task_group (name, created, uuid) VALUES (#{name}, #{created}, #{uuidString})")
	@SelectKey(
			before = false, 
			keyColumn = "id", 
			keyProperty = "id", 
			resultType = Long.class, 
			statement = "SELECT LAST_INSERT_ID()", 
			statementType = StatementType.STATEMENT)
	long insert(TaskGroup taskGroup);
	
	@Update("UPDATE task_group SET name = #{name} WHERE id = #{id}")
	long update(TaskGroup taskGroup);
	
	@Delete("DELETE FROM task_group WHERE id = #{id}")
	long delete(long id);

	@Select("SELECT * FROM task_group WHERE id = #{id}")
	TaskGroup selectById(long id);

	@Select("SELECT * FROM task_group WHERE id = #{id}")
	TaskGroup selectByIdExt(long id);

	@Select("SELECT * FROM task_group WHERE uuid = #{uuidString}")
	TaskGroup selectByUuid(String uuidString);

	@Select("SELECT * FROM task_group WHERE uuid = #{uuidString}")
	TaskGroup selectByUuidExt(String uuidString);

}
