package server.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Value("${vehicle.process.error.message}") private String errorMessage;

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<String> handleException() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
		}
		
		return ResponseEntity.internalServerError().body(errorMessage);
	}

}
