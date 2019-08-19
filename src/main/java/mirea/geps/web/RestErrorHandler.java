package mirea.geps.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.geps.core.service.NotFoundException;
import mirea.geps.web.dto.ErrorCode;
import mirea.geps.web.dto.ErrorCodeEnum;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
@ResponseBody
public class RestErrorHandler {
    private static final String UNEXPECTED_CODE = "service.unexpected";
    private static final String BAD_REQUEST = "service.bad_request";
    private static final String NOT_FOUND = "service.not_found";

    private MessageSource messageSource;

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorCode handle(Exception e) {
        log.error(e.getMessage(), e);
        String message = messageSource.getMessage(UNEXPECTED_CODE, null, Locale.getDefault());
        return new ErrorCode(ErrorCodeEnum.UNEXPECTED_ERROR.getCode(), message);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorCode handle(NotFoundException e) {
        log.warn(e.getMessage(), e.getMessage());
        String message = e.getMessage();
        return new ErrorCode(ErrorCodeEnum.NOT_FOUND.getCode(), message);
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorCode handle(MethodArgumentNotValidException e) {
        log.warn("Bad request.", e.getMessage());
        String message = messageSource.getMessage(BAD_REQUEST, null, Locale.getDefault());
        return new ErrorCode(ErrorCodeEnum.BAD_REQUEST.getCode(), message);
    }
}
