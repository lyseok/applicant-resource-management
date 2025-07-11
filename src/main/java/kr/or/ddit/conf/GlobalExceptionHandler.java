package kr.or.ddit.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.InterviewQuestionScoreInsertException;
import kr.or.ddit.common.exception.VideoInterviewCreateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(VideoInterviewCreateException.class)
	public Map<String, Object> handleVideoInterviewCreateException(VideoInterviewCreateException ex) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("success", false);
	    map.put("msg", ex.getMessage());
	    log.info("Exception Message ---> {}", ex.getMessage());
	    return map;
	}
	

    @ResponseBody
    @ExceptionHandler(InterviewQuestionScoreInsertException.class)
    public Map<String, Object> handleInterviewQuestionScoreInsertException(InterviewQuestionScoreInsertException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("msg", ex.getMessage());
        log.info("Exception Message ---> {}", ex.getMessage());
        return map;
    }
    
    @ResponseBody
    @ExceptionHandler(DataInsertException.class)
    public Map<String, Object> handleDataInsertException(DataInsertException ex) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("success", false);
    	map.put("msg", ex.getMessage());
    	log.info("Exception Message ---> {}", ex.getMessage());
    	return map;
    }
}