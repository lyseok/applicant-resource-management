package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjTaskVO;

@Mapper
public interface PrjTaskMapper {
	public List<PrjTaskVO> selectPrjTaskList();
	public PrjTaskVO selectPrjTaskByPk(String taskNo);
	public int insertPrjTask(PrjTaskVO prjTask);
	public int updatePrjTask(PrjTaskVO prjTask);
	public int deletePrjTask(String taskNo);
}
