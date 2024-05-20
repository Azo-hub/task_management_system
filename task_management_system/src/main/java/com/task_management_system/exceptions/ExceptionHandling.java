package com.task_management_system.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.task_management_system.utilities.HttpCustomResponse;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please "
                                                                    + "contact administrator";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed "
            + "on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while " + "processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. " + "Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. "
            + "If this is an error, please contact administrator";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String ERROR_PATH = "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpCustomResponse> accountDisabledException() {

        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpCustomResponse> badCredentialsException() {

        return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpCustomResponse> accessDeniedException() {

        return createHttpResponse(HttpStatus.FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpCustomResponse> lockedException(LockedException exception) {

        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpCustomResponse> usernameExistException(UsernameExistException exception) {

        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpCustomResponse> tokenExpiredException(TokenExpiredException exception) {

        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpCustomResponse> emailExistException(EmailExistException exception) {

        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<HttpCustomResponse> invalidTokenException(InvalidTokenException exception) {

        return createHttpResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpCustomResponse> userNotFoundException(UserNotFoundException exception) {

        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpCustomResponse> emailNotFoundException(EmailNotFoundException exception) {

        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<HttpCustomResponse> passwordNotMatchException(PasswordNotMatchException exception) {

        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpCustomResponse> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpCustomResponse> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpCustomResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }



    private ResponseEntity<HttpCustomResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        HttpCustomResponse httpResponse = new HttpCustomResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());

        return new ResponseEntity<>(httpResponse, httpStatus);

    }

    public String getErrorPath() {
        return ERROR_PATH;
    }



}
