package com.stc.filesmanager.controller;

import com.stc.filesmanager.exceptions.DuplicateItemException;
import com.stc.filesmanager.exceptions.InvalidParentItemException;
import com.stc.filesmanager.exceptions.NoItemFoundException;
import com.stc.filesmanager.exceptions.NoPermissionException;
import com.stc.filesmanager.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidParentItemException.class)
    public ResponseEntity<ErrorResponseModel> handleInvalidParent(Exception ex){
        var error = new ErrorResponseModel("NO_INVALID_PARENT", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoItemFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleNoItemsFound(Exception ex){
        var error = new ErrorResponseModel("NO_ITEM_FOUND", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<ErrorResponseModel> handleNoPermission(Exception ex){
        var error = new ErrorResponseModel("NO_PERMISSION", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<ErrorResponseModel> handleDuplicateItems(Exception ex){
        var error = new ErrorResponseModel("DUPLICATE_ITEM", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handle(Exception ex){
        var error = new ErrorResponseModel("INTERNAL_ERROR", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
