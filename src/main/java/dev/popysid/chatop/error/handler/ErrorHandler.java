package dev.popysid.chatop.error.handler;

import dev.popysid.chatop.error.exception.AuthException;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.error.exception.RentalException;
import dev.popysid.chatop.error.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMessageException(MessageException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage());
        return createErrorResponse(400, e, request);
    }


    @ExceptionHandler(RentalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleARentalException(RentalException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage());
        return createErrorResponse(400, e, request);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthException(AuthException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage());
        return createErrorResponse(401, e, request);
    }


    private ErrorResponse createErrorResponse(int statusCode, Exception e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(statusCode);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setPath(request.getContextPath() + request.getServletPath());
        errorResponse.setDateTime(LocalDateTime.now());
        return errorResponse;
    }

}
