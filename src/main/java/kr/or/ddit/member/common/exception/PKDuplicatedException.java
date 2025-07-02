package kr.or.ddit.member.common.exception;

public class PKDuplicatedException extends RuntimeException {
	private final Object pk;

	public PKDuplicatedException(Object pk) {
		super(String.format("%s PK 중복", pk));
		this.pk = pk;
	}
	
	
}
