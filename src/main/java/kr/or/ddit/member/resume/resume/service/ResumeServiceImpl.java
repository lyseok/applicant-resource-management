package kr.or.ddit.member.resume.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
	private final ResumeMapper mapper;
	
	@Override
	public List<ResumeVO> readResumeList() {
		return mapper.selectResumeList();
	}

	@Override
	public ResumeVO readResumeDetail(String no) {
		return mapper.selectResumeDetail(no);
	}

	@Override
	public void createResume(ResumeVO vo) {
		mapper.insertResume(vo);
	}

	@Override
	public void editResume(ResumeVO vo) {
		mapper.updateResume(vo);
	}

	@Override
	public void removeResume(String no) {
		mapper.deleteResume(no);
	}

}
