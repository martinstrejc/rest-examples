package cz.wicketstuff.examples.spring.persistence.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
	
	@Select("SELECT t.* FROM task t WHERE t.task_group_id = #{taskGroupId} ORDER BY ${sqlSort}")
	@ResultMap("task")
	List<Task> selectAll(@Param("taskGroupId") long taskGroupId, @Param("sqlSort") String sqlSort);

	@Select("SELECT tg.*, t.id as t_id, t.name as t_name, t.created as t_created, t.uuid as t_uuid FROM task_group tg LEFT JOIN task t ON (t.task_group_id = tg.id) ORDER BY tg.created")
	@ResultMap("taskExt")
	List<Task> selectAllExt();

	@Select("SELECT count(*) FROM task t WHERE t.task_group_id = #{taskGroupId}")
	long countAll(long taskGroupId);
	

	@Insert("INSERT INTO task (name, created, uuid, status, task_group_id) VALUES (#{name}, #{created}, #{uuidString}, #{status}, #{taskGroup.id})")
	@SelectKey(
			before = false, 
			keyColumn = "id", 
			keyProperty = "id", 
			resultType = Long.class, 
			statement = "SELECT LAST_INSERT_ID()", 
			statementType = StatementType.STATEMENT)
	long insert(Task task);
	
	@Update("UPDATE task SET name = #{name}, status = #{status} WHERE id = #{id}")
	long update(Task task);
	
	@Delete("DELETE FROM task WHERE id = #{taskId}")
	long delete(long id);

	@Select("SELECT * FROM task WHERE id = #{id}")
	Task selectById(long id);

	@Select("SELECT t.*, tg.id as tg_id, tg.name as tg_name, tg.created as tg_created, tg.uuid as tg_uuid FROM task t LEFT JOIN task_group tg ON (tg.id = t.task_group_id) WHERE t.id = #{id}")
	@ResultMap("taskExt")
	Task selectByIdExt(long id);

	@Select("SELECT * FROM task WHERE uuid = #{uuidString}")
	Task selectByUuid(String uuidString);

	@Select("SELECT t.*, tg.id as tg_id, tg.name as tg_name, tg.created as tg_created, tg.uuid as tg_uuid FROM task t LEFT JOIN task_group tg ON (tg.id = t.task_group_id) WHERE t.id = #{uuidString}")
	@ResultMap("taskExt")
	Task selectByUuidExt(String uuidString);
	
	public static String sqlSort(Task.Sort sort, boolean ascending) {
		OrderBuilder builder = new OrderBuilder();
		switch (sort) {
		case ID:
			return builder.column("id", ascending).build();
		case NAME:
			return builder.column("name", ascending).column("created", ascending).column("id", ascending).build();			
		case CREATED:
			return builder.column("created", ascending).column("id", ascending).build();
		case PRIORITY:
			return builder.column("priority", ascending).column("created", ascending).column("id", ascending).build();
		case STATUS:
			return builder.column("status", ascending).column("created", ascending).column("id", ascending).build();
		default:
			throw new IllegalArgumentException("Unsupported sorting " + sort);
		}
	}
	
}
