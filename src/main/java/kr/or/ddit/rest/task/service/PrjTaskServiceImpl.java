package kr.or.ddit.rest.task.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.project.PrjTaskMapper;
import kr.or.ddit.vo.project.PrjTaskStatisticsVO;
import kr.or.ddit.vo.project.PrjTaskVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PrjTaskServiceImpl implements PrjTaskService {
	private final PrjTaskMapper prjTaskMapper;

    public List<PrjTaskVO> readProjectTaskList(String prjNo, String status, String userId) {
        return prjTaskMapper.selectProjectTaskList(prjNo, status, userId);
    }
    
    public PrjTaskVO selectTaskByNo(String taskNo) {
        return prjTaskMapper.selectTaskByNo(taskNo);
    }
    
    public PrjTaskVO createTask(PrjTaskVO prjTaskVO) {
        prjTaskMapper.insertTask(prjTaskVO);

        return prjTaskMapper.selectTaskByNo(prjTaskVO.getTaskNo());
    }
    
    public PrjTaskVO updateTask(String taskNo, PrjTaskVO prjTask) {
        PrjTaskVO existingTask = prjTaskMapper.selectTaskByNo(taskNo);
        if (existingTask == null) {
            throw new NotFoundException("Task not found: " + taskNo);
        }

        existingTask.setTaskName(prjTask.getTaskName());
        existingTask.setTaskStatus(prjTask.getTaskStatus());
        existingTask.setTaskStatusName(prjTask.getTaskStatusName());
        existingTask.setDetailContent(prjTask.getDetailContent());
        existingTask.setStartDate(prjTask.getStartDate());
        existingTask.setDueDate(prjTask.getDueDate());
        existingTask.setPriorityCode(prjTask.getPriorityCode());
        existingTask.setPriorityCodeName(prjTask.getPriorityCodeName());
        existingTask.setUpperTaskNo(prjTask.getUpperTaskNo());
        existingTask.setProgressRate(prjTask.getProgressRate());

        prjTaskMapper.updateTask(existingTask);

        return prjTaskMapper.selectTaskByNo(taskNo);
    }
    
    public int deleteTask(String taskNo) {
        // 예: 실제로는 로그인 유저ID도 받아서 DELETE_USER_ID로 세팅
        String deleteUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return prjTaskMapper.updateTaskDelete(taskNo, deleteUserId);
    }
    
    @Override
    public PrjTaskVO updateTaskStatus(String taskNo, String taskStatus) {
        prjTaskMapper.updateTaskStatus(taskNo, taskStatus);
        return prjTaskMapper.selectTaskByNo(taskNo);
    }
    
    @Override
    public PrjTaskVO updateTaskProgress(String taskNo, String progressRate) {
        prjTaskMapper.updateTaskProgress(taskNo, progressRate);
        return prjTaskMapper.selectTaskByNo(taskNo);
    }
    
    @Override
    public PrjTaskStatisticsVO getTaskStatistics(String prjNo) {
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        return prjTaskMapper.selectTaskStatisticsByPrjNo(prjNo, today);
    }
}
