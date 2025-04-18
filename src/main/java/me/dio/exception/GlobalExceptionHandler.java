package me.dio.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestControllerAdvice diz ao Spring: "Ei, esta classe é especial! Ela vai cuidar de todas as exceções que acontecerem em qualquer controlador REST da aplicação."
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class); // Este é o sistema de registro (log) que usamos para anotar informações importantes sobre os problemas que acontecem. | private static final: Significa que este logger pertence à sta classe somente e não muda | LoggerFactory.getLogger(GlobalExceptionHandler.class): Cria um logger específico para esta classe.

    @ExceptionHandler(BusinessException.class) // @ExceptionHandler(BusinessException.class): Diz "Este method cuida especificamente de exceções do tipo BusinessException. Em qualquer lugar da aplicação, quando essa exceção acontecer, ela vai ser detectada pelo GlobalExceptionHandler e vai chamar este method abaixo para tratar essa exceção. O GlobalExceptionHandler é um lugar onde você pode colocar todas as exceções que você quer que ele trate.
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY); // O código de status HTTP 422 (UNPROCESSABLE_ENTITY), que significa "Entendi seu pedido, mas não posso processá-lo porque tem algo errado com os dados"
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNoContentException() { // handleNoContentException(): É o nome do method (note que ele não recebe a exceção como parâmetro, porque o Spring vai tratar todas as exceções que não forem especificadas como @ExceptionHandler).
        return new ResponseEntity<>("Resource ID not found.", HttpStatus.NOT_FOUND); // O código de status HTTP 404 (NOT_FOUND), que é o famoso erro que você vê quando uma página da web não existe
    }

    @ExceptionHandler(Throwable.class) // @ExceptionHandler(Throwable.class): Diz "Este method cuida de qualquer tipo de exceção" (Throwable é a superclasse de todas as exceções em Java)
    public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException) { // handleUnexpectedException(Throwable unexpectedException): É o nome do método e ele recebe a exceção que aconteceu
        String message = "Unexpected server error."; // String message = "Unexpected server error.": Cria uma mensagem genérica para o usuário
        LOGGER.error(message, unexpectedException); // LOGGER.error(message, unexpectedException): Registra o erro completo no log (diário de ocorrências) para que os desenvolvedores possam investigar depois
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR); // A mensagem genérica (não mostramos os detalhes técnicos para o usuário)
    } // O código de status HTTP 500 (INTERNAL_SERVER_ERROR), que significa "Algo deu errado no servidor"
}

