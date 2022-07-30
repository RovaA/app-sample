package mg.rova.demo.infrastructure.adapters.primary.exception;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot found entity, mismatched id")
	@ExceptionHandler(EntityNotFoundException.class)
	public void handleEntityNotFoundException() {
		LOGGER.error("Entity not found.");
	}
}
