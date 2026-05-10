package es.iesclaradelrey.da2d1a.tiendadrmstgabd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja el error 404 (No encontrado)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso no encontrado");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Maneja el error 409 (Conflicto, por ejemplo si el producto no estaba en el carro)
    @ExceptionHandler(CarritoException.class)
    public ProblemDetail handleCarritoException(CarritoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflicto en la operación del carrito");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Manejador genérico para cualquier otro error 500
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ha ocurrido un error inesperado");
        problemDetail.setTitle("Error interno del servidor");
        return problemDetail;
    }
}
