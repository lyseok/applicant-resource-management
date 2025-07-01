package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"taskNo", "prjNo", "userId"})
public class TasksManagerVO implements Serializable {
	private String taskNo;
	private String prjNo;
	private String userId;
}
