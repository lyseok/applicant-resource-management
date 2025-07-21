package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.FilesVO;
@Mapper
public interface FileMapper {
	public List<FilesVO> selectFileList();

	public FilesVO selectFileByPk(FilesVO vo);
	
	public int insertFile(FilesVO vo);
	
	public int updateSource(FilesVO vo);
	
	public int updateFile(FilesVO vo);
	
	public int deleteFile(String fileNo);
}
