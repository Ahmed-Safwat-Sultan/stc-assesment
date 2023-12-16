package com.stc.filesmanager.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileCreationRequest {

    String parentName;
    String userEmail;
    MultipartFile file;
}
