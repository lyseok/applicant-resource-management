package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.TasksManagerVO;

@Mapper
public interface TaskManagerMapper {
	public List<TasksManagerVO> selectTasksManagerList();
	public TasksManagerVO selectTasksManagerByPk(TasksManagerVO tasksManager);
	public int insertTasksManager(TasksManagerVO tasksManager);
	public int deleteTasksManager(TasksManagerVO tasksManager);
}
