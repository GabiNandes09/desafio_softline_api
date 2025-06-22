package com.gabrielfernandes.Desafio_SoftLine.errors;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = UnrecognizedPropertyException.class)
    public ResponseEntity<Object> handlerUnrecognizedProperty(UnrecognizedPropertyException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("erro", "Campo não reconhecido no Json");
        errorDetails.put("campo_invalido", ex.getPropertyName());
        errorDetails.put("mensagem", ex.getOriginalMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ErrorResponse> handlePropertyValueException(PropertyValueException ex) {
        String campo = ex.getPropertyName();
        String entidade = ex.getEntityName();

        ErrorResponse error = new ErrorResponse(
                "Campo obrigatório ausente",
                "O campo '" + campo + "' não pode ser nulo na entidade '" + entidade + "'.",
                ex.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());

        Throwable rootCause = ex.getMostSpecificCause();
        String msg = rootCause != null ? rootCause.getMessage().toLowerCase() : "";

        if (msg.contains("duplicate entry")) {
            errorResponse.setErro("Duplicidade de dados");
            errorResponse.setMensagem("Já existe um registro com esses dados.");
        } else if (msg.contains("foreign key constraint fails")) {
            errorResponse.setErro("Referência inválida");
            errorResponse.setMensagem("Relacionamento inválido: verifique os dados referenciados.");
        } else {
            errorResponse.setErro("Erro de integridade");
            errorResponse.setMensagem("Violação de integridade no banco de dados.");
        }

        errorResponse.setDetalhe(ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String detalhe = ex.getMostSpecificCause().getMessage();
        String mensagem;

        if (detalhe != null && detalhe.contains("Unrecognized token")) {
            mensagem = "JSON malformado. Certifique-se de que todos os campos estejam entre aspas e corretos.";
        } else {
            mensagem = "Erro ao processar o corpo da requisição. Verifique o JSON enviado.";
        }

        ErrorResponse error = new ErrorResponse(
                "Erro na leitura do JSON",
                mensagem,
                detalhe,
                LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNaoEncontrado(EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                "Recurso não encontrado",
                ex.getMessage(),
                "Verifique se o ID está correto",
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("null")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String nomeParametro = ex.getName();
        String tipoEsperado = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "tipo desconhecido";
        String valorInvalido = ex.getValue() != null ? ex.getValue().toString() : "nulo";

        ErrorResponse error = new ErrorResponse(
                "Parâmetro inválido",
                "O parâmetro '" + nomeParametro + "' com valor '" + valorInvalido
                        + "' não pode ser convertido para o tipo " + tipoEsperado,
                ex.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                "Rota não encontrada",
                "O endpoint solicitado não foi encontrado.",
                "Verifique se a URL está correta: " + ex.getRequestURL(),
                LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("erro", "Erro de validação");
        response.put("erros", errors);
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String message = "Método HTTP " + ex.getMethod() + " não suportado para este endpoint. Métodos suportados: "
                + ex.getSupportedHttpMethods();
        ErrorResponse error = new ErrorResponse(
                "Método não suportado",
                message,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String message = "Parâmetro obrigatório '" + ex.getParameterName() + "' está faltando na requisição.";
        ErrorResponse error = new ErrorResponse(
                "Parâmetro ausente",
                message,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(
                "Acesso negado",
                "Você não tem permissão para acessar este recurso.",
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Erro interno",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
