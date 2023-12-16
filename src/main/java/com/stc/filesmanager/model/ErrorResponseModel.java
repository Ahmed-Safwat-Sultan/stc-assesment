package com.stc.filesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseModel {
    String errorCode;
    String errorDescription;
}
