package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;

@Mapper
public interface CareerMapper {
	// 목록 조회
	public List<CareerVO> selectCareerList(String no);
	// 단건 조회
	public CareerVO selectCareerDetail(CareerVO vo);
	// 등록
	public int insertCareer(CareerVO vo);
	// 수정
	public int updateCareer(CareerVO vo);
	// 삭제
	public int deleteCareer(String no);
	// 논리적 삭제
	public int updateCareerDelete(String no);
	
	//추가
	public CareerVO selectCareerDetail(String careerNo);
}
