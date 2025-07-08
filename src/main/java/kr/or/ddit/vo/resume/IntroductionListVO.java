package kr.or.ddit.vo.resume;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class IntroductionListVO implements Serializable{
	@Valid
	private List<IntroductionVO> introductionList;
	
	 // Spring이 이 VO를 인스턴스화할 때 사용할 기본 생성자, null 방지를 위한 ArrayList로 초기화
	public IntroductionListVO() {
        this.introductionList = new ArrayList<>();
    }
}
