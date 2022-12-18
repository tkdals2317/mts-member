package com.midasin.mtsmember.infra.adviser;

import com.midasin.mtsmember.infra.CustomException;
import com.midasin.mtsmember.infra.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ErrorDto> customException(CustomException ce){
        log.error(ExceptionUtils.getStackTrace(ce));

        final Optional<ErrorMessage> errorMessage = Optional.of(ce)
                .map(CustomException::getErrorMessage);

        final HttpStatus httpStatus = errorMessage.map(ErrorMessage::getHttpStatus)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        final String message = errorMessage.map(ErrorMessage::getMessage)
                .orElse(StringUtils.EMPTY);

        return new ResponseEntity<>(
                new ErrorDto(httpStatus, message), httpStatus);
    }

}
