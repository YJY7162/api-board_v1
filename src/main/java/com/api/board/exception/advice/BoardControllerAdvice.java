package com.api.board.exception.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.api.board.controller.UploadController;
import com.api.board.exception.domain.ResponseError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackageClasses = UploadController.class)
public class BoardControllerAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseError handleMultipartException(MaxUploadSizeExceededException e) {
        log.error("[exceptionHandler] ex", e);
        return new ResponseError("MaxUploadSizeEx", e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseError handleMultipartExceptio1n(IOException e) {
        log.error("[exceptionHandler] ex", e);
        return new ResponseError("MaxUploadSizeEx", e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseError handleMultipartException2(IllegalStateException e) {
        log.error("[exceptionHandler] ex", e);
        return new ResponseError("MaxUploadSizeEx", e.getMessage());
    }
}
