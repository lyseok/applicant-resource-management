package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.FileVO;
@Mapper
public interface FileMapper {
	public List<FileVO> selectFileList();

	public FileVO selectFileByPk(FileVO vo);
	
	public int insertFile(FileVO vo);
	
	public int updateFile(FileVO vo);
	
	public int deleteFile(FileVO vo);
}
