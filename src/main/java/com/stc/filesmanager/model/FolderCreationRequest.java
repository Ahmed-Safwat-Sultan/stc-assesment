package com.stc.filesmanager.model;

import lombok.Data;

@Data
public class FolderCreationRequest {
    String folderName;
    String parentName;
    String userEmail;

}
