package kr.or.ddit.company.recruitment.talentpool.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.MailDTO;

public interface TalentPoolService {
	public Map<String, Object> readResumeByMyScrab(Map<String, Object> params);
	public Map<String, Object> readResumeByFilter(Map<String, Object> params);
    public List<String> getSavedTalentList();
    public void updateTalentList(List<String> addList, List<String> removeList);
    public Map<String, Object> readSetupList();
    
    public int postMailLogic(List<MailDTO> mailList);
}
