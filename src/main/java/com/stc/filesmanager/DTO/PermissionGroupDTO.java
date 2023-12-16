package com.stc.filesmanager.DTO;

import com.stc.filesmanager.entity.Item;
import com.stc.filesmanager.entity.Permission;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PermissionGroupDTO {


    private Set<Item> items;

    private List<Permission> permissions;

}
