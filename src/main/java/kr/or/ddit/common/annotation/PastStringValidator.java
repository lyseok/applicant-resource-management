package kr.or.ddit.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PastStringValidator implements ConstraintValidator<PastString, String> {

	private static final List<DateTimeFormatter> FORMATTERS = List.of(
		    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
		    DateTimeFormatter.ofPattern("yyyyMMdd")
		);


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	    if (value == null || value.isBlank()) return true;
	    for (DateTimeFormatter formatter : FORMATTERS) {
	        try {
	            LocalDate date = LocalDate.parse(value, formatter);
	            return date.isBefore(LocalDate.now());
	        } catch (DateTimeParseException ignored) {}
	    }
	    return false;
	}
}
