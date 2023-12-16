package com.stc.filesmanager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.filesmanager.entity.PermissionGroup;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class PermissionDTO {


    private String userEmail;

    private String permissionLevel;

    @JsonIgnore
    private PermissionGroup permissionGroup;


}
